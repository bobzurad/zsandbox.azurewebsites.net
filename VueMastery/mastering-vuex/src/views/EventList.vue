<template>
  <div>
    <h1>Events Listing</h1>
    <EventCard v-for="event in events" :key="event.id" :event="event" />
    <p>Click Count: {{ clickCount }}</p>
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
  computed: mapState(['clickCount']),
  methods: {
    incrementCount() {
      this.$store.commit('INCREMENT_CLICK_COUNT')
    }
  }
}
</script>
