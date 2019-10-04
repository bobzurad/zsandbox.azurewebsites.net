<template>
  <div>
    <h1>Events for {{ user.name }}</h1>
    <EventCard v-for="event in event.events" :key="event.id" :event="event" />
    <template v-if="page != 1">
      <router-link
        :to="{ name: 'event-list', query: { page: page - 1 } }"
        rel="prev"
        >Prev Page</router-link
      >
    </template>
    <template v-if="page > 1 && page * perPage < event.eventsTotalCount"
      >&nbsp;|&nbsp;</template
    >
    <template v-if="page * perPage < event.eventsTotalCount">
      <router-link :to="{ name: 'event-list', query: { page: page + 1 } }"
        >Next Page</router-link
      >
    </template>
    <p>Click Count: {{ counter.clickCount }}</p>
    <input type="number" v-model.number="incrementBy" />
    <button @click="incrementCount">Increment</button>
  </div>
</template>

<script>
import EventCard from '@/components/EventCard.vue'
import { mapState } from 'vuex'

export default {
  components: {
    EventCard
  },
  data() {
    return {
      incrementBy: 1,
      perPage: 3
    }
  },
  created() {
    this.$store.dispatch('event/fetchEvents', {
      perPage: this.perPage,
      page: this.page
    })
  },
  // a computed property will only re-evaluate when some of its reactive dependencies have changed
  computed: {
    page() {
      return parseInt(this.$route.query.page) || 1
    },
    ...mapState({
      event: state => state.event,
      user: state => state.user.user,
      counter: state => state.counter
    })
  },
  // a method invocation will always run the function whenever a re-render happens
  methods: {
    incrementCount() {
      this.$store.dispatch('updateCount', this.incrementBy, {
        root: true
      })
    }
  },
  getters: {
    // a getter that can access other getters
    activeTodosCount: (state, getters) => {
      return state.todos.length - getters.doneTodos.length
    },
    doneTodos: state => {
      return state.todos.filter(todo => todo.done)
    }
  }
}
</script>
