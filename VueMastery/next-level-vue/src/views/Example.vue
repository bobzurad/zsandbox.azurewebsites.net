<template>
  <form @submit.prevent="submit">
    <input
      type="email"
      placeholder="What's your email?"
      v-model="email"
      @blur="$v.email.$touch()"
      :class="{ error: $v.email.$error }"
    />
    <div v-if="$v.email.$error">
      <p v-if="!$v.email.email" class="errorMessage">
        Please enter a valid email
      </p>
      <p v-if="!$v.email.required" class="errorMessage">Email is required</p>
    </div>
    <button :disabled="$v.$invalid" type="submit">Submit</button>
    <p v-if="$v.$anyError" class="errorMessage">Please correct all fields</p>

    <!-- filters example -->
    <p>{{ comment | reply('bro') | shout | exclaim }}</p>
  </form>
</template>

<script>
import { required, email } from 'vuelidate/lib/validators'
import { exampleMixin } from '../mixins/exampleMixin'

export default {
  mixins: [exampleMixin],
  data() {
    return {
      comment: 'no one cares',
      email: null,
      message: 'I take priority from the mixin' // this overrites 'message' in the mixin
    }
  },
  validations: {
    email: {
      required,
      email
    }
  },
  created() {
    this.hello()
    console.log(this.message)
  },
  filters: {
    exclaim(value) {
      return value + '!!!'
    },
    reply(value, name) {
      return `${value}, ${name}`
    },
    shout(value) {
      return value.toUpperCase()
    }
  },
  methods: {
    hello() {
      // this method overrites the hello() method in the mixin
      console.log('Hello from the component')
    },
    submit() {
      this.$v.$touch() // make the form dirty
      if (!this.$v.$invalid) {
        console.log(`Form submitted: ${this.email}`)
      }
    }
  }
}
</script>

<style scoped>
.errorMessage {
  color: red;
}
</style>
