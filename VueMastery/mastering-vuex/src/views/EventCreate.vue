<template>
  <div>
    <h1>Create Event, {{ userName }}</h1>
    <p>This event was created by {{ userName }}</p>
    <ul>
      <li v-for="cat in categories" :key="cat">{{ cat }}</li>
    </ul>
    <p>{{ localComputed }}</p>
    <p>There are {{ catLength }} items listed.</p>
    <p>{{ getEventById(2) }}</p>
  </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex'

export default {
  /* the old way
  computed: {
    userName() {
      return this.$store.state.user.name
    }
  }
  */

  //the new way
  computed: {
    localComputed() {
      return 'i did not come from the store'
    },
    catLength() {
      return this.$store.getters.catLength
    },
    //... is JavaScript's new object-spread operator https://github.com/tc39/proposal-object-rest-spread
    ...mapGetters(['getEventById']),
    ...mapState({
      userName: state => state.user.name,
      userId: state => state.user.id,
      categories: 'categories' //can also do it this way for top level items in state
    })
  }

  /* can also do it this way, mapping state directly
  computed: mapState(['user', 'categories'])
  */
}
</script>
