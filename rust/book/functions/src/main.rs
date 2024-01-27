fn main() {
    print_labeled_measurement(5, 'h');

    // variables must be bound to expressions, not statements!
    let y = {
        let x = 3;
        x + 1 // no semi-colon here so that an expression is returned and bound to y
    };
    println!("The value of y is: {y}"); // 4

    // call a function that returns a value
    let z = five();
    println!("The value of z is: {z}"); // 5

    // call another function
    let a = plus_one(z);
    println!("The value of a is: {a}"); // 6
}

fn print_labeled_measurement(value: i32, unit_label: char) {
    println!("The measurement is: {value}{unit_label}");
}

fn five() -> i32 {
    5
}

fn plus_one(x: i32) -> i32 {
    x + 1 // ending this with a semi-colon would cause a compiler error becaues the semi-colon would change this line from an expression to a statement (and statements don't evaluate to a value)
          // return x + 1; has the same effect as the previous line
}
