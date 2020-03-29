/**********************************************************
  This script will produce the following output:

> .load 4-reactivity-with-proxies.js
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

let deps = new Map()

Object.keys(data).forEach(key => {
  deps.set(key, new Dep())
})

let data_without_proxy = data

data = new Proxy(data_without_proxy, {
  get(obj, key) {
    deps.get(key).depend()
    return obj[key]
  },
  set(obj, key, newVal) {
    obj[key] = newVal
    deps.get(key).notify()
    return true
  }
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

deps.set("discount", new Dep());  // Need a new dep for our property
data["discount"] = 5; // Add our new property

let salePrice = 0; 

watcher(() => {  // New code to watch which includes our reactive property
  salePrice = data.price - data.discount;
});

console.log("salePrice = " + salePrice);
data.discount = 7.5;  // This should be reactive, and rerun the watcher.
console.log("salePrice = " + salePrice);