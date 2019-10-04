// This module exports state, mutations, actions, and getters separately so that we can also
// have private variables and methods.

import EventService from '@/services/EventService.js'

// state defined in this module is private to this module
// - to access state from other modules, use rootState in the actions (example below)

// mutations, actions, and getters are registered with the global namespace
// - this allows logic to be called in multiple modules for an action with the same name
// - e.g. "doThis" action triggers logic in multiple modules

// namespace this module to avoid naming collisions with mutations, actions, and getters
// - (namespace name is event)
export const namespaced = true

export const state = {
  event: {},
  events: [], // this doesn't store ALL of the events, just the events that were fetched
  eventsTotalCount: 0 // this is the total number of events, not just events.length
}

// mutations are synchronous
// it's good practice to keep mutations within actions
// - only actions (from this module!) should call mutations, this prevents the need for future refactoring
// - mutations should not be called from other modules
// - components will have methods that dispatch actions in the store
export const mutations = {
  ADD_EVENT(state, event) {
    state.events.push(event)
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
}

// actions are asynchronous
export const actions = {
  createEvent({ commit, dispatch, rootState }, event) {
    // example of how we can call an action from another module
    // (mutations, actions, and getters are in the global namespace)
    dispatch('updateCount', 1000, { root: true })

    // example of using the state from store.js (global namespace)
    console.log('User creating event is ' + rootState.user.user.name)

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
  }
}

export const getters = {
  getEventById: state => id => {
    return state.events.find(event => event.id === id)
  }
}
