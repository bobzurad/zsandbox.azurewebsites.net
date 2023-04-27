import { GetServerSideProps } from "next";
import Head from "next/head";
import Link from "next/link";
import { Space, Row, Col } from "antd";
import { useAtomValue } from "jotai";
import { useHydrateAtoms } from "jotai/utils";
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
  // load props data into atom
  useHydrateAtoms(new Map([[categoriesAtom, data]]));

  const categories = useAtomValue(categoriesAtom);

  return (
    <>
      <Head>
        <title>EcomApp - Categories</title>
      </Head>
      <Row justify="space-between">
        <Col xs={24} lg={24} xl={12}>
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
        </Col>
        <Col xs={24} lg={24} xl={12}>
          <AddCategory />
        </Col>
      </Row>
      <Row justify={"space-between"}>
        <Col span={24}>
          <Link href="/">← Back to Home</Link>
        </Col>
      </Row>
    </>
  );
}
