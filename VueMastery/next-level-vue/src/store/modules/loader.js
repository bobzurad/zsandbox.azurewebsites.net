import NProgress from 'nprogress'

export const namespaced = true

export const state = {
  waitingCount: 0
}

export const mutations = {
  DECREMENT_COUNT(state) {
    state.waitingCount--
  },
  INCREMENT_COUNT(state) {
    state.waitingCount++
  }
}

export const actions = {
  doneLoading({ commit, state }) {
    commit('DECREMENT_COUNT')
    if (state.waitingCount === 0) {
      NProgress.done()
    }
  },
  startLoading({ commit, state }) {
    if (state.waitingCount === 0) {
      NProgress.start()
    }
    commit('INCREMENT_COUNT')
  }
}
