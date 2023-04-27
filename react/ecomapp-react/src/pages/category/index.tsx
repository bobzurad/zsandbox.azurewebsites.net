import { GetServerSideProps } from "next";
import Head from "next/head";
import Link from "next/link";
import { Space, Row, Col } from "antd";
import { useHydrateAtoms } from "jotai/utils";
import { categoriesAtom } from "../../context/category";
import AddCategory from "../../components/category/addCategory";
import ListCategory from "../../components/category/listCategory";
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
  // load props data into atom
  useHydrateAtoms(new Map([[categoriesAtom, data]]));

  return (
    <>
      <Head>
        <title>EcomApp - Categories</title>
      </Head>
      <main className={styles.main}>
        <div className={styles.center}>
          <Row>
            <Col xs={24} lg={24} xl={12} className={styles.col}>
              <ListCategory />
            </Col>
            <Col xs={24} lg={24} xl={12} className={styles.col}>
              <AddCategory />
            </Col>
          </Row>
        </div>
        <div className={styles.center}>
          <Row>
            <Col span={24} className={styles.col}>
              <Link href="/">← Back to Home</Link>
            </Col>
          </Row>
        </div>
      </main>
    </>
  );
}
