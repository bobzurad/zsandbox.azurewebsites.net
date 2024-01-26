fn main() {
    // integer types
    let a: i8 = 127; // -128 to 127
    let b: u8 = 255; // 0 to 255
    let c: i16 = 32767; // -32768 to 32767
    let d: u16 = 65535; // 0 to 65535
    let e: i32 = 2147483647; // integer default, -2,147,483,648 to 2,147,483,647
    let f: u32 = 4294967295; // 0 to 4,294,967,295
    let g: i64 = 9223372036854775807; // -9,223,372,036,854,775,807 to 9,223,372,036,854,775,808
    let h: u64 = 18446744073709551615; // 0 to 18,446,744,073,709,551,615
    let i: i128 = 170141183460469231731687303715884105727; // -170,141,183,460,469,231,731,687,303,715,884,105,728 to 170,141,183,460,469,231,731,687,303,715,884,105,727
    let j: u128 = 340282366920938463463374607431768211455; // 0 to 340,282,366,920,938,463,463,374,607,431,768,211,455
    let k: isize = 9223372036854775807; // depends on size of architecture the program runs on 32 bit, 64 bit, etc (assuming 64 bit)
    let l: usize = 18446744073709551615; // assuming 64 bit

    println!("The max signed 8 bit integer is {a}");
    println!("The max unsigned 8 bit integer is {b}");
    println!("The max signed 16 bit integer is {c}");
    println!("The max unsigned 16 bit integer is {d}");
    println!("The max signed 32 bit integer is {e}");
    println!("The max unsigned 32 bit integer is {f}");
    println!("The max signed 64 bit integer is {g}");
    println!("The max unsigned 64 bit integer is {h}");
    println!("The max signed 128 bit integer is {i}");
    println!("The max unsigned 128 bit integer is {j}");
    println!("The max signed arch integer is {k}");
    println!("The max unsigned integer is {l}");

    // floating point types
    let m = 2.01; // f64 (floating point default)
    let n: f32 = 56.7 / 32.2;
    println!("The values of the floating point types are {m} and {n}");

    // booleans
    let o = true;
    let p: bool = false;
    println!("Boolean values are {o} and {p}");

    // char types
    let q = 'z';
    let r: char = 'ℤ'; // with explicit type annotation
    let heart_eyed_cat = '😻';
    println!("Char values are {q}, {r}, and {heart_eyed_cat}");

    // tuple
    let tup = (500, 6.4, 'A');
    let tup2: (i32, f64, bool) = (501, 6.5, true);

    let (s, t, u) = tup;
    println!("The value of tup's first item is {s}");
    println!("The value of tup's second item is {t}");
    println!("The value of tup's third item is {u}");
    let v = tup2.1;
    println!("The value of tup2's second item is {v}");

    // array
    let w = [1, 2, 3, 4, 5];
    let x: [i32; 5] = [1, 2, 3, 4, 5]; // explicitly set size
    let y = [3; 5]; // produces [3, 3, 3, 3, 3]
    let z = w[0];
    let aa = x[1];
    let bb = y[3];
    println!("The first value in the first array is {z}");
    println!("The second value in the second array is {aa}");
    println!("The fourth value in the third array is {bb}");
}
