import * as React from 'react';
import {List, Text} from 'react-native-paper';
import {ICategoryModel} from '../models/category';

interface Props {
  categories: ICategoryModel[];
}

const CategoryList = (props: Props) => {
  const {categories} = props;

  return (
    <>
      {categories.length > 0 &&
        categories.map(category => {
          return (
            <List.Item
              key={category.id}
              title={category.categoryName}
              description={category.description}
            />
          );
        })}
      {categories.length === 0 && <Text>There are no Categories</Text>}
    </>
  );
};

export default CategoryList;
