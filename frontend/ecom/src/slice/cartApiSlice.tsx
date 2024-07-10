import { CART_URL } from "../constants/constants";
import { apiSlice } from "./apiSlice";
import { productDetails } from "./productApiSlice";

export type CartItemDetails = {
  cartItemId: number;
  cart: number;
  product: productDetails;
  quantity: number;
};

export type CartDetails = {
  cartId: number;
  user: number;
  cartDtos: CartItemDetails[];
};

export const cartApiSlice = apiSlice.injectEndpoints({
  endpoints: (builder) => ({
    getCartByUserId: builder.query<CartDetails, number>({
      query: (id) => ({
        method: "GET",
        url: `${CART_URL}/user/${id}`,
      }),
      providesTags: ["Cart"],
    }),
    addToCart: builder.mutation<void, { cartId: number; productId: number }>({
      query: ({ cartId, productId }) => ({
        url: `${CART_URL}`,
        method: "POST",
        params: { cartId, productId },
      }),
      invalidatesTags: ["Cart"],
    }),
    deleteFromcart: builder.mutation<void, number>({
      query: (id) => ({
        url: `${CART_URL}/${id}`,
        method: "DELETE",
      }),
      invalidatesTags: ["Cart"],
    }),
    updateCartQuantity: builder.mutation<
      void,
      { id: number; quantity: number }
    >({
      query: ({ id, quantity }) => ({
        url: `${CART_URL}/${id}`,
        method: "PUT",
        params: { quantity },
      }),
      invalidatesTags: ["Cart"],
    }),
  }),
});

export const {
  useGetCartByUserIdQuery,
  useAddToCartMutation,
  useDeleteFromcartMutation,
  useUpdateCartQuantityMutation,
} = cartApiSlice;
