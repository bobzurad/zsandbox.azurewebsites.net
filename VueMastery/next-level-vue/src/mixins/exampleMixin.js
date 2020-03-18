// code in mixins run before code in components

export const exampleMixin = {
  data() {
    return {
      message: 'Hello from the mixin'
    }
  },
  created() {
    this.logMessage()
  },
  methods: {
    hello() {
      console.log('Hello from the mixin')
    },
    logMessage() {
      console.log(this.message)
    }
  }
}
