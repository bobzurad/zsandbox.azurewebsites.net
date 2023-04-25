import { useState, useRef, useContext, useEffect } from "react";
import { GetServerSideProps } from "next";
import Head from "next/head";
import Link from "next/link";
import { Button, Input, Space, Form } from "antd";
import type { FormInstance } from "antd";
import { CategoryContext, CategoryContextType } from "@/context/category";
import { postData } from "@/util/api";
import { validateMessages } from "@/util/form";
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
  const { categories, updateCategories } = useContext(
    CategoryContext
  ) as CategoryContextType;

  useEffect(() => {
    updateCategories(data);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [data]);

  const [newCategoryName, setNewCategoryName] = useState("");
  const [newCategoryDescription, setNewCategoryDescription] = useState("");
  const [newCategoryIsLoading, setNewCategoryIsLoading] = useState(false);

  const newCategoryFormRef = useRef<FormInstance>(null);

  const newCategoryFormLayout = {
    labelCol: { span: 8 },
    wrapperCol: { span: 16 },
  };

  const newCategoryHandler = () => {
    setNewCategoryIsLoading(true);

    postData("http://localhost:8080/category/create/", {
      categoryName: newCategoryName,
      description: newCategoryDescription,
      imageUrl: "https://picsum.photos/200",
    })
      .then(async (responseData) => {
        // on successful post, reset form and react state
        newCategoryFormRef.current?.resetFields();
        setNewCategoryName("");
        setNewCategoryDescription("");
        setNewCategoryIsLoading(false);
        // refresh categories
        const request = await fetch("http://localhost:8080/category/");
        const data = await request.json();
        updateCategories(data);
      })
      .catch((reason) => {
        console.log(reason);
        setNewCategoryIsLoading(false);
      });
  };

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
          <Form
            {...newCategoryFormLayout}
            name="add-category"
            ref={newCategoryFormRef}
            style={{ minWidth: 500 }}
            validateMessages={validateMessages}
            onFinish={newCategoryHandler}
          >
            <Form.Item
              name="newCategoryName"
              label="Name"
              rules={[{ required: true }]}
            >
              <Input
                size="large"
                value={newCategoryName}
                onChange={(event) =>
                  setNewCategoryName(event.currentTarget.value)
                }
              />
            </Form.Item>
            <Form.Item name="newCategoryDescription" label="Description">
              <Input.TextArea
                size="large"
                value={newCategoryDescription}
                onChange={(event) =>
                  setNewCategoryDescription(event.currentTarget.value)
                }
              />
            </Form.Item>
            <Form.Item
              wrapperCol={{ ...newCategoryFormLayout.wrapperCol, offset: 8 }}
            >
              <Button
                type="primary"
                size="large"
                htmlType="submit"
                loading={newCategoryIsLoading}
              >
                Add Category
              </Button>
            </Form.Item>
          </Form>
        </div>
        <Link href="/">← Back to Home</Link>
      </main>
    </>
  );
}
