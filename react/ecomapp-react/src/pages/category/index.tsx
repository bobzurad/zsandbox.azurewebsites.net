import { GetServerSideProps } from "next";
import Head from "next/head";
import Link from "next/link";

export const getServerSideProps: GetServerSideProps = async ({ res }) => {
  const request = await fetch("http://localhost:8080/category/");
  const data = await request.json();
  return {
    props: {
      data,
    },
  };
};

export default function Home({
  data,
}: {
  data: {
    id: number;
    categoryName: string;
    description: string;
    imageUrl: string;
  }[];
}) {
  console.log(data);
  return (
    <>
      <Head>
        <title>EcomApp - Categories</title>
      </Head>
      <main className="flex min-h-screen flex-col items-center justify-between p-24">
        <div className="columns-3 w-full gap-12">
          <div>Categories</div>
          {data.length === 0 ? (
            <>
              <div>There are no categories.</div>
            </>
          ) : (
            <>
              {data.forEach((category) => {
                <>
                  <div>{category.categoryName}</div>
                  <div>{category.description}</div>
                </>;
              })}
            </>
          )}
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
    </>
  );
}
