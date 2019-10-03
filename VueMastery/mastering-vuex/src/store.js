import Vue from 'vue'
import Vuex from 'vuex'
import EventService from '@/services/EventService.js'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: { id: 'abc123', name: 'Adam Jahr' },
    categories: [
      'sustainability',
      'nature',
      'animal welfare',
      'housing',
      'education',
      'food',
      'community'
    ],
    clickCount: 0,
    event: {},
    events: [], // this doesn't store ALL of the events, just the events that were fetched
    eventsTotalCount: 0, // this is the total number of events, not just events.length
    todos: [
      { id: 1, text: '...', done: true },
      { id: 2, text: '...', done: false },
      { id: 3, text: '...', done: true },
      { id: 4, text: '...', done: false }
    ]
  },
  // mutations are synchronous
  // it's good practice to keep mutations within actions
  // - only actions should call mutations, this prevents the need for future refactoring
  // - components will have methods that dispatch actions in the store
  mutations: {
    ADD_EVENT(state, event) {
      state.events.push(event)
    },
    INCREMENT_CLICK_COUNT(state, value) {
      state.clickCount += value
    },
    SET_EVENT(state, event) {
      state.event = event
    },
    SET_EVENTS(state, events) {
      state.events = events
    },
    SET_EVENTS_TOTAL_COUNT(state, eventsTotalCount) {
      state.eventsTotalCount = eventsTotalCount
    }
  },
  // actions are asynchronous
  actions: {
    createEvent({ commit }, event) {
      return EventService.postEvent(event).then(() => {
        commit('ADD_EVENT', event)
      })
    },
    fetchEvent({ commit, getters }, id) {
      // first check if we have the event already
      const event = getters.getEventById(id)

      if (event) {
        commit('SET_EVENT', event)
      } else {
        // we don't have the event locally, so use the API to fetch it
        EventService.getEvent(id)
          .then(response => {
            commit('SET_EVENT', response.data)
          })
          .catch(error => {
            console.log('Error getting event: ' + error.response)
          })
      }
    },
    // payload (second param) can be single variable OR an object
    fetchEvents({ commit }, { perPage, page }) {
      EventService.getEvents(perPage, page)
        .then(response => {
          commit('SET_EVENTS', response.data)
          commit('SET_EVENTS_TOTAL_COUNT', response.headers['x-total-count'])
        })
        .catch(error => {
          console.log('There was an error: ' + error.response)
        })
    },
    updateCount({ state, commit }, value) {
      if (state.user) {
        commit('INCREMENT_CLICK_COUNT', value)
      }
    }
  },
  getters: {
    catLength: state => {
      return state.categories.length
    },
    getEventById: state => id => {
      return state.events.find(event => event.id === id)
    },
    doneTodos: state => {
      return state.todos.filter(todo => todo.done)
    },
    // a getter that can access other getters
    activeTodosCount: (state, getters) => {
      return state.todos.length - getters.doneTodos.length
    }
  }
})
