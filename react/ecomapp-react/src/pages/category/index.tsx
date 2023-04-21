import Link from "next/link";

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      <div className="columns-3 w-full gap-12">
        <div>Categories</div>
        <div>test</div>
        <div>test2</div>
      </div>
      <div className="columns-3 w-full gap-12">
        <div>Add Category</div>
        <div>
          <input />
        </div>
        <div>
          <button>Add</button>
        </div>
      </div>
      <Link href="/">← Back to Home</Link>
    </main>
  );
}
