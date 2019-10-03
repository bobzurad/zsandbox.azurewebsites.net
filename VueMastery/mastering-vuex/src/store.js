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
    events: [
      { id: 1, title: '...', organizer: '...' },
      { id: 2, title: '...', organizer: '...' },
      { id: 3, title: '...', organizer: '...' },
      { id: 4, title: '...', organizer: '...' },
      { id: 5, title: '...', organizer: '...' }
    ],
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
    }
  },
  // actions are asynchronous
  actions: {
    createEvent({ commit }, event) {
      return EventService.postEvent(event).then(() => {
        commit('ADD_EVENT', event)
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
