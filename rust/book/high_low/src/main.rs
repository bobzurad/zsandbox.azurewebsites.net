use rand::Rng;
use std::{cmp::Ordering, io};

fn main() {
    println!("Guess the number! 1 to 100");

    let secret_number = rand::thread_rng().gen_range(1..=100);

    loop {
        println!("Please input your guess.");

        let mut guess = String::new();

        io::stdin()
            .read_line(&mut guess)
            .expect("Failed to read line");

        let guess: u32 = match guess.trim().parse() {
            Ok(num) => num,     // parse is successfull, return guessed number
            Err(_) => continue, // if an error occurs while parsing, ignore it and jump to next loop cycle
        };

        match guess.cmp(&secret_number) {
            Ordering::Less => println!("Too small!"),
            Ordering::Greater => println!("Too big!"),
            Ordering::Equal => {
                println!("You win! The secret number was {secret_number}");
                break;
            }
        }
    }
}
