export default class FlashMessage {

  constructor(message) {
    this.message = message;
  }

  renderAlert() {
    alert(this.message);
  }

  renderLog() {
    console.log(this.message);
  }
}

//another way

class FlashMessage2 {

}

export { FlashMessage2 };
