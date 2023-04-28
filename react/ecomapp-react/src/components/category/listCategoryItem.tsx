import { useState, useRef } from "react";
import { List, Avatar, Button, Modal, Form, Input } from "antd";
import type { FormInstance } from "antd";
import { ICategoryModel } from "@/models/category";
import { validateMessages } from "@/util/form";
import styles from "@/styles/category/ListCategoryItem.module.css";

export default function ListCategoryItem(props: { category: ICategoryModel }) {
  const [category, setCategory] = useState(props.category);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const editCategoryFormRef = useRef<FormInstance>(null);

  const editCategoryFormLayout = {
    labelCol: { span: 8 },
    wrapperCol: { span: 16 },
  };

  const showModal = () => {
    setIsModalOpen(true);
  };

  const handleOk = () => {
    // TODO: save updated category
    setIsModalOpen(false);
  };

  const handleCancel = () => {
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
        onOk={handleOk}
        onCancel={handleCancel}
      >
        <Form
          {...editCategoryFormLayout}
          name="edit-category"
          ref={editCategoryFormRef}
          validateMessages={validateMessages}
        >
          <Form.Item
            name="editCategoryName"
            label="Name"
            rules={[{ required: true }]}
          >
            <Input
              size="large"
              value={category.categoryName}
              defaultValue={category.categoryName}
              onChange={(event) => console.log(event.currentTarget.value)}
            />
          </Form.Item>
          <Form.Item name="editCategoryDescription" label="Description">
            <Input.TextArea
              size="large"
              value={category.description}
              onChange={(event) => console.log(event.currentTarget.value)}
            />
          </Form.Item>
        </Form>
      </Modal>
    </>
  );
}
