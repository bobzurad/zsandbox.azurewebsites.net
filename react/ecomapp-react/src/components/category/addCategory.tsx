import { useState, useRef } from "react";
import Head from "next/head";
import { Button, Input, Form } from "antd";
import type { FormInstance } from "antd";
import { useSetAtom } from "jotai";
import { categoriesAtom } from "../../context/category";
import { postData } from "@/util/api";
import { validateMessages } from "@/util/form";
import { ICategoryModel } from "@/models/category";

export default function AddCategory() {
  const setCategories = useSetAtom(categoriesAtom);

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
        setCategories(data as ICategoryModel[]);
      })
      .catch((reason) => {
        console.log(reason);
        setNewCategoryIsLoading(false);
      });
  };

  return (
    <>
      <Head>
        <title>Add Category</title>
      </Head>
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
            onChange={(event) => setNewCategoryName(event.currentTarget.value)}
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
    </>
  );
}
