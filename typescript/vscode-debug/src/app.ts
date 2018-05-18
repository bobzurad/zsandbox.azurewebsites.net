class HelloTypeScript {
  constructor(public message: string) {
  }
}

var hello = new HelloTypeScript("Hello World")
console.log(hello.message)
