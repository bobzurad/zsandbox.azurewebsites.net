fn main() {
    // standard loop
    let mut counter = 0;
    let result = loop {
        counter += 1;

        if counter == 10 {
            break counter * 2;
        }
    };
    println!("The result is: {result}");

    // labeled loop
    let mut count = 0;
    // loop labels start with '
    'counting_up: loop {
        println!("count = {count}");
        let mut remaining = 10;
        loop {
            println!("remaining = {remaining}");
            if remaining == 9 {
                break;
            }
            if count == 2 {
                break 'counting_up;
            }
            remaining -= 1;
        }
        count += 1;
    }
    println!("End count = {count}");

    // while loop
    let mut number = 3;
    while number != 0 {
        println!("{number}!");
        number -= 1;
    }
    println!("LIFTOFF!!!");

    // for loop
    let a = [10, 20, 30, 40, 50];
    for element in a {
        println!("The value is: {element}");
    }

    // another for loop
    for num in (1..4).rev() {
        println!("{num}!");
    }
    println!("LIFTOFF!!!");
}
