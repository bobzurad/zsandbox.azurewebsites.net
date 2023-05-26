import { GetServerSideProps } from "next";
import Head from "next/head";
import Link from "next/link";
import { Row, Col } from "antd";
import { useHydrateAtoms } from "jotai/utils";
import { IProductModel } from "@/models/product";
import { productsAtom } from "@/context/product";
import styles from "../../styles/product/Product.module.css";

export const getServerSideProps: GetServerSideProps = async ({ res }) => {
  const request = await fetch("http://localhost:8080/product/");
  const data = await request.json();

  return {
    props: {
      data,
    },
  };
};

export default function Home({ data }: { data: IProductModel[] }) {
  // load props data into atom
  useHydrateAtoms(new Map([[productsAtom, data]]));

  return (
    <>
      <Head>
        <title>EcomApp - Products</title>
      </Head>
      <main className={styles.main}>
        <div className={styles.center}>
          <Row>
            <Col xs={24} lg={24} xl={12} className={styles.col}>
              TODO: List Products here
            </Col>
            <Col xs={24} lg={24} xl={12} className={styles.col}>
              TODO: Add Products here
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
