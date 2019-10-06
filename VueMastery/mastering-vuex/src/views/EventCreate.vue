<template>
  <div>
    <h1>Create Event, {{ userName }}</h1>
    <form @submit.prevent="createEvent">
      <label>Select one of the {{ catLength }} categories</label>
      <select v-model="event.category">
        <option v-for="cat in categories" :key="cat">{{ cat }}</option>
      </select>
      <h3>Name & describe your event</h3>
      <div class="field">
        <label>Title</label>
        <input
          v-model="event.title"
          type="text"
          placeholder="Add an event title"
        />
      </div>
      <div class="field">
        <label>Description</label>
        <input
          v-model="event.description"
          type="text"
          placeholder="Add a description"
        />
      </div>
      <h3>Where is your event?</h3>
      <div class="field">
        <label>Location</label>
        <input
          v-model="event.location"
          type="text"
          placeholder="Add a location"
        />
      </div>
      <h3>When is your event?</h3>
      <div class="field">
        <label>Date</label>
        <datepicker v-model="event.date" placeholder="Select a date" />
      </div>
      <div class="field">
        <label>Select a time</label>
        <select v-model="event.time">
          <option v-for="time in times" :key="time">{{ time }}</option>
        </select>
      </div>
      <input type="submit" class="button -fill-gradient" value="Submit" />
    </form>
    <p>{{ localComputed }}</p>
    <p>{{ getEventById(2) }}</p>
  </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex'
import Datepicker from 'vuejs-datepicker'

export default {
  components: {
    Datepicker
  },
  data() {
    const times = []
    for (let i = 1; i <= 24; i++) {
      times.push(i + ':00')
    }
    return {
      event: this.createFreshEvent(),
      times,
      categories: this.$store.state.categories
    }
  },
  computed: {
    /* the old way to create a computed property for userName
    userName() {
      return this.$store.state.user.name
    } */
    localComputed() {
      return 'i did not come from the store'
    },
    /*
    catLength() { // this is a duplicate of mapGetters(['catLength'])
      return this.$store.getters.catLength
    },
    getEventById() {  // this is a duplicate of mapGetters('event', ['getEventById'])
      return this.$store.getters['event/getEventById']
    },
    */
    //... is JavaScript's new object-spread operator https://github.com/tc39/proposal-object-rest-spread
    ...mapGetters('event', ['getEventById']),
    ...mapGetters(['catLength']),
    ...mapState({
      userName: state => state.user.user.name,
      userId: state => state.user.user.id
      //categories: 'categories' //can also do it this way for top level items in state
    })
    /* can also do it this way, mapping state directly
  computed: mapState(['user', 'categories'])
  */
  },
  methods: {
    createEvent() {
      this.$store
        .dispatch('event/createEvent', this.event)
        .then(() => {
          this.$router.push({
            name: 'event-show',
            params: { id: this.event.id }
          })
          this.event = this.createFreshEvent()
        })
        .catch(e => {
          console.log('there was a problem submitting your event: ' + e)
        })
    },
    createFreshEvent() {
      const user = this.$store.state.user.user
      const id = Math.floor(Math.random() * 10000000)
      return {
        id: id,
        category: '',
        organizer: user,
        title: '',
        description: '',
        location: '',
        date: '',
        time: '',
        attendees: []
      }
    }
  }
}
</script>

<style scoped>
.field {
  margin-bottom: 24px;
}
</style>
