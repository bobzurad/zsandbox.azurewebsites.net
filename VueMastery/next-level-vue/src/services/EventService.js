import axios from 'axios'
import store from '@/store/store'

const apiClient = axios.create({
  baseURL: 'http://localhost:3000',
  withCredentials: false,
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json'
  }
})

/* axios interceptors to start and stop progress bar */
apiClient.interceptors.request.use(config => {
  store.dispatch('loader/startLoading')
  return config
})
apiClient.interceptors.response.use(response => {
  store.dispatch('loader/doneLoading')
  return response
})

export default {
  getEvents(perPage, page) {
    return apiClient.get('/events?_limit=' + perPage + '&_page=' + page)
  },
  getEvent(id) {
    return apiClient.get('/events/' + id)
  },
  postEvent(event) {
    return apiClient.post('/events', event)
  }
}
