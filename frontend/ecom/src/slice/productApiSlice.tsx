import { PRODUCT_URL } from "../constants/constants";
import { apiSlice } from "./apiSlice";

export type productDetails = {
  productId: number;
  name: string;
  description: string;
  price: number;
  rating: number;
  discount: number;
  category: number;
  quantity: number;
  quantity_sold: number;
};

interface Image {
  image: Blob;
}

export interface ImageResponse {
  imageBlob: Blob; // Adjust type based on your image format
}

export const productApiSlice = apiSlice.injectEndpoints({
  endpoints: (builder) => ({
    getAllProducts: builder.query<productDetails[], void>({
      query: () => ({
        url: `${PRODUCT_URL}`,
      }),
    }),
    getProductImage: builder.query<Image, number>({
      query: (id) => ({
        url: `${PRODUCT_URL}/image/${id}`,
      }),
    }),
  }),
});

export const { useGetAllProductsQuery, useGetProductImageQuery } =
  productApiSlice;
