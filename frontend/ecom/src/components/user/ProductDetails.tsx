import React, { useState } from "react";
import { useParams } from "react-router-dom";
import {
  useGetProductByIdQuery,
  useGetProductImageQuery,
} from "../../slice/productApiSlice";
import Button from "../UI/Button";
import {
  useAddToCartMutation,
  useGetCartByUserIdQuery,
} from "../../slice/cartApiSlice";
import { toast } from "react-toastify";

const ProductDetails = () => {
  const { id } = useParams();
  const { data, isLoading } = useGetProductByIdQuery(parseInt(id));
  const { data: imageBlob } = useGetProductImageQuery(parseInt(id));
  const { data: cart } = useGetCartByUserIdQuery(1);
  const [addToCart] = useAddToCartMutation();
  function handleAddToCart(
    event: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ): void {
    toast.success("Product Added to Cart");
    addToCart({ cartId: cart.cartId, productId: data.productId });
  }

  console.log(data);
  return (
    !isLoading && (
      <div className="d-flex flex-row justify-content-between">
        <div className="text-center mb-3 ">
          {!isLoading && (
            <img
              src={`data:image/jpeg;base64,${imageBlob.image}`}
              alt="product"
              style={{ height: "500px", width: "500px" }}
              className="shadow"
            />
          )}
        </div>
        <div className="ms-5">
          <p className="h1 text-capitalize mb-3">{data.name}</p>
          <p className="h4 m-0 mb-2">
            ₹ {(data.price - (data.price * data.discount) / 100).toFixed(0)}
          </p>
          <p className="discountClass mb-3">
            <span className="productPrice">₹ {data.price}</span> &nbsp;
            <span className="discount">{data.discount}% off</span>
          </p>
          <p className="text-capitalize">{data.description}</p>
          <Button
            className="btn bg-dark p-1 text-white rounded-2 shadow-lg"
            style={{ width: "350px" }}
            onClick={handleAddToCart}
          >
            ADD to Cart
          </Button>
        </div>
      </div>
    )
  );
};

export default ProductDetails;
