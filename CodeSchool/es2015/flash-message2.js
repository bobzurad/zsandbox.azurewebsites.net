export function alertMessage(message) {
  alert(message);
}

export function logMessage(message) {
  console.log(message);
}

//another way

function alertMessage2(message) {
  alert(message);
}

function logMessage2(message) {
  console.log(message);
}

export { alertMessage2, logMessage2 };
