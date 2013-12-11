//TODO: figure out side scrolling or zooming
// view.viewSize bounds scrollBy()
// mouse events

console.log(view);

var sunX = -400;
var earthX = 900;
var jupiterX = 2000;

var sun = new Path.Circle({
    center: new Point(sunX, view.center.y),
    radius: 500,
    fillColor: 'yellow'
});

var earth = new Path.Circle({
    center: new Point(earthX, view.center.y),
    radius: 5,
    fillColor: 'blue'
});

var jupiter = new Path.Circle({
    center: new Point(jupiterX, view.center.y),
    radius: 30,
    fillColor: 'violet'
});

function onResize(event) {
    // Whenever the window is resized, reposition
    sun.position = new Point(sunX, view.center.y);
    earth.position = new Point(earthX, view.center.y);
    jupiter.position = new Point(jupiterX, view.center.y);
}
