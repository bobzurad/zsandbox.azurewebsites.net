<template>
  <div>
    <h1>Events Listing</h1>
    <EventCard v-for="event in events" :key="event.id" :event="event" />
    <p>Click Count: {{ clickCount }}</p>
    <input type="number" v-model.number="incrementBy" />
    <button @click="incrementCount">Increment</button>
  </div>
</template>

<script>
import EventCard from '@/components/EventCard.vue'
import EventService from '@/services/EventService.js'
import { mapState } from 'vuex'

export default {
  components: {
    EventCard
  },
  data() {
    return {
      incrementBy: 1,
      events: []
    }
  },
  created() {
    EventService.getEvents()
      .then(response => {
        this.events = response.data
      })
      .catch(error => {
        console.log('There was an error: ' + error.response)
      })
  },
  // a computed property will only re-evaluate when some of its reactive dependencies have changed
  computed: mapState(['clickCount']),
  // a method invocation will always run the function whenever a re-render happens
  methods: {
    incrementCount() {
      this.$store.dispatch('updateCount', this.incrementBy)
    }
  }
}
</script>
