import { useState, useRef } from "react";
import { List, Avatar, Button, Modal, Form, Input } from "antd";
import type { FormInstance } from "antd";
import { useSetAtom } from "jotai";
import { categoriesAtom } from "@/context/category";
import { ICategoryModel } from "@/models/category";
import { validateMessages } from "@/util/form";
import styles from "@/styles/category/ListCategoryItem.module.css";
import { postData } from "@/util/api";

export default function ListCategoryItem(props: { category: ICategoryModel }) {
  const [category, setCategory] = useState(props.category);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isSaving, setIsSaving] = useState(false);

  const setCategories = useSetAtom(categoriesAtom);
  const editCategoryFormRef = useRef<FormInstance>(null);

  const editCategoryFormLayout = {
    labelCol: { span: 8 },
    wrapperCol: { span: 16 },
  };

  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleOk = () => {
    // save updated values
    setIsSaving(true);
    postData("http://localhost:8080/category/update/" + category.id, category)
      .then(async (responseData) => {
        // refresh categories
        const request = await fetch("http://localhost:8080/category/");
        const data = await request.json();
        setCategories(data as ICategoryModel[]);
        setIsSaving(false);
        setIsModalOpen(false);
      })
      .catch((reason) => {
        console.log(reason);
        setIsSaving(false);
      });
  };

  const handleCancel = () => {
    // revert to original values
    setCategory(props.category);
    setIsModalOpen(false);
  };

  return (
    <>
      <List.Item
        actions={[
          <Button
            key={`list-edit-${category.id}`}
            size="small"
            onClick={showModal}
          >
            Edit
          </Button>,
        ]}
      >
        <List.Item.Meta
          avatar={<Avatar src={category.imageUrl} />}
          title={<span className={styles.title}>{category.categoryName}</span>}
          description={
            <span className={styles.description}>{category.description}</span>
          }
        />
      </List.Item>
      <Modal
        title="Edit Category"
        open={isModalOpen}
        footer={[
          <Button key="cancel" onClick={handleCancel}>
            Cancel
          </Button>,
          <Button key="ok" type="primary" loading={isSaving} onClick={handleOk}>
            Ok
          </Button>,
        ]}
      >
        <Form
          {...editCategoryFormLayout}
          name="edit-category"
          ref={editCategoryFormRef}
          validateMessages={validateMessages}
          initialValues={{
            editCategoryName: category.categoryName,
            editCategoryDescription: category.description,
          }}
        >
          <Form.Item
            name="editCategoryName"
            label="Name"
            rules={[{ required: true }]}
          >
            <Input
              size="large"
              value={category.categoryName}
              onChange={(event) =>
                setCategory({
                  ...category,
                  categoryName: event.currentTarget.value,
                })
              }
            />
          </Form.Item>
          <Form.Item name="editCategoryDescription" label="Description">
            <Input.TextArea
              size="large"
              value={category.description}
              onChange={(event) =>
                setCategory({
                  ...category,
                  description: event.currentTarget.value,
                })
              }
            />
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
}
