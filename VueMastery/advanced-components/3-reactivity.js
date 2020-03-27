/**********************************************************
  This script will produce the following output:

> .load 3-reactivity.js
undefined
> total
10
> salesPrice
4.5
> price
Uncaught ReferenceError: price is not defined
> data.price
5
> data.price = 20
20
> total
40
> salesPrice
18
> data.quantity = 10
10
> total
200
> salesPrice
18
**********************************************************/

let data = { price: 5, quantity: 2 }
let target, total, salesPrice

class Dep {
  constructor() {
    this.subscribers = []
  }
  depend() {
    if (target && !this.subscribers.includes(target)) {
      this.subscribers.push(target)
    }
  }
  notify() {
    this.subscribers.forEach(sub => sub())
  }
}

Object.keys(data).forEach(key => {
  let internalValue = data[key]

  const dep = new Dep()

  Object.defineProperty(data, key, {
    get() {
      dep.depend()  // remember the target we're running
      return internalValue
    },
    set(newVal) {
      internalValue = newVal
      dep.notify()  // rerun saved targets
    }
  })
})

function watcher(myFunc) {
  target = myFunc
  target()
  target = null
}

watcher(() => {
  total = data.price * data.quantity
})

watcher(() => {
  salesPrice = data.price * 0.9
})
