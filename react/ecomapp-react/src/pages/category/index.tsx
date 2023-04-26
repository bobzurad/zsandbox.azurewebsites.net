import { useEffect } from "react";
import { GetServerSideProps } from "next";
import Head from "next/head";
import Link from "next/link";
import { Space } from "antd";
import { useAtom } from "jotai";
import { categoriesAtom } from "../../context/category";
import AddCategory from "../../components/category/addCategory";
import styles from "../../styles/category/Category.module.css";
import { ICategoryModel } from "@/models/category";

export const getServerSideProps: GetServerSideProps = async ({ res }) => {
  const request = await fetch("http://localhost:8080/category/");
  const data = await request.json();

  return {
    props: {
      data,
    },
  };
};

export default function Home({ data }: { data: ICategoryModel[] }) {
  const [categories, setCategories] = useAtom(categoriesAtom);

  // set categories loaded from getServerSideProps
  useEffect(() => {
    setCategories(data);
  }, [setCategories, data]);

  return (
    <>
      <Head>
        <title>EcomApp - Categories</title>
      </Head>
      <main className={styles.main}>
        <div className={styles.center}>
          <Space size="large">
            <div>Categories</div>
            {categories.length === 0 ? (
              <>
                <div>There are no categories.</div>
              </>
            ) : (
              <>
                {categories.map((category) => {
                  return (
                    <div key={category.id}>
                      <div>{category.categoryName}</div>
                      <div>{category.description}</div>
                    </div>
                  );
                })}
              </>
            )}
          </Space>
        </div>
        <div className={styles.center}>
          <AddCategory />
        </div>
        <Link href="/">← Back to Home</Link>
      </main>
    </>
  );
}
