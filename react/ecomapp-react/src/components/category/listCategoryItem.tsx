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
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [isDeleteModalOpen, setIsDeleteModalOpen] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const setCategories = useSetAtom(categoriesAtom);
  const editCategoryFormRef = useRef<FormInstance>(null);

  const editCategoryFormLayout = {
    labelCol: { span: 8 },
    wrapperCol: { span: 16 },
  };

  const refreshCategories = async () => {
    const request = await fetch("http://localhost:8080/category/");
    const data = await request.json();
    setCategories(data as ICategoryModel[]);
  };

  const showEditModal = () => {
    setIsEditModalOpen(true);
  };

  const showDeleteModal = () => {
    setIsDeleteModalOpen(true);
  };

  const handleUpdate = () => {
    // save updated values
    setIsLoading(true);
    postData("http://localhost:8080/category/update/" + category.id, category)
      .then(async (responseData) => {
        // refresh categories
        await refreshCategories();
        setIsLoading(false);
        setIsEditModalOpen(false);
      })
      .catch((reason) => {
        console.log(reason);
        setIsLoading(false);
      });
  };

  const handleCancelUpdate = () => {
    // revert to original values
    setCategory(props.category);
    setIsEditModalOpen(false);
  };

  const handleDelete = () => {
    setIsLoading(true);
    postData("http://localhost:8080/category/delete/" + category.id)
      .then(async (responseData) => {
        // refresh categories
        await refreshCategories();
        setIsLoading(false);
        setIsDeleteModalOpen(false);
      })
      .catch((reason) => {
        console.log(reason);
        setIsLoading(false);
      });
  };

  const handleCancelDelete = () => {
    setIsDeleteModalOpen(false);
  };

  return (
    <>
      <List.Item
        actions={[
          <Button
            key={`list-edit-${category.id}`}
            size="small"
            onClick={showEditModal}
          >
            Edit
          </Button>,
          <Button
            key={`list-delete-${category.id}`}
            size="small"
            onClick={showDeleteModal}
            danger
          >
            Delete
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
        title="Delete Category"
        open={isDeleteModalOpen}
        onCancel={handleCancelDelete}
        onOk={handleDelete}
      >
        <p>Are you sure you want to delete the following Category?</p>
        <p>{category.categoryName}</p>
        <p>{category.description}</p>
      </Modal>
      <Modal
        title="Edit Category"
        open={isEditModalOpen}
        footer={[
          <Button key="cancel" onClick={handleCancelUpdate}>
            Cancel
          </Button>,
          <Button
            key="ok"
            type="primary"
            loading={isLoading}
            onClick={handleUpdate}
          >
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
