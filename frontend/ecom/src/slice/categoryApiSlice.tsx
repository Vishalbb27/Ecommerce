import { CATEGORY_URL } from "../constants/constants";
import { apiSlice } from "./apiSlice";

export type CategoryDetails = {
  id: number;
  name: string;
  description: string;
};

export const categoryApiSlice = apiSlice.injectEndpoints({
  endpoints: (builder) => ({
    getCategories: builder.query<CategoryDetails[], void>({
      query: () => ({
        url: `${CATEGORY_URL}`,
      }),
    }),
    getCategoryById: builder.query<CategoryDetails, number>({
      query: (id) => ({
        url: `${CATEGORY_URL}/${id}`,
      }),
    }),
  }),
});

export const { useGetCategoriesQuery, useLazyGetCategoryByIdQuery } =
  categoryApiSlice;
