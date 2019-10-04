// This module does not allow for private state because the state, mutations, actions, and getters
// are contained within export default

// Is this namespaced? Can it be?

export default {
  state: {
    clickCount: 0
  },
  mutations: {
    INCREMENT_CLICK_COUNT(state, value) {
      state.clickCount += value
    }
  },
  actions: {
    updateCount({ commit }, value) {
      commit('INCREMENT_CLICK_COUNT', value)
    }
  },
  getters: {}
}
