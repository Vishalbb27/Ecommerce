import React from "react";
import {
  CartItemDetails,
  useDeleteFromcartMutation,
  useUpdateCartQuantityMutation,
} from "../../slice/cartApiSlice";
import { useGetProductImageQuery } from "../../slice/productApiSlice";
import { FaCaretUp } from "react-icons/fa";
import { FaCaretDown } from "react-icons/fa";
import Button from "../UI/Button";
import { RiDeleteBin6Line } from "react-icons/ri";
import "../../css/button.css";
import { toast } from "react-toastify";
export type ErrorDetails = {
  timeStamp: string;
  message: string;
  details: string;
};

export type CartItemProps = {
  cartItem: CartItemDetails;
};
const PaymentItem = ({ cartItem }: CartItemProps) => {
  const { data: imageBlob, isLoading } = useGetProductImageQuery(
    cartItem.product.productId
  );
  const [updateCartQuantity] = useUpdateCartQuantityMutation();
  const [deleteFromcart] = useDeleteFromcartMutation();
  function handleDelete(
    event: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ): void {
    deleteFromcart(cartItem.cartItemId);
  }

  function handleIncreaseQuantity(
    event: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ): void {
    if (cartItem.quantity + 1 >= 6) {
      toast.info("You can only buy 5 pieces of this item.");
    } else {
      updateCartQuantity({ id: cartItem.cartItemId, quantity: 1 });
    }
  }

  function handleDecreaseQuantity(
    event: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ): void {
    if (cartItem.quantity - 1 <= 0) {
      toast.info("You should buy atleast one item.");
    } else {
      updateCartQuantity({ id: cartItem.cartItemId, quantity: -1 });
    }
  }

  return (
    <div className="d-flex flex-row justify-content-between align-items-center mb-3 p-2 shadow-sm rounded-3">
      <div className="text-center d-flex flex-row align-items-center">
        {!isLoading && (
          <img
            src={`data:image/jpeg;base64,${imageBlob.image}`}
            alt="product"
            style={{ height: "50px", width: "100%" }}
            className="shadow rounded"
          />
        )}
        <div className="ms-3 d-flex flex-column align-items-start">
          <p
            className="m-0 text-capitalize"
            style={{ fontSize: "20px", fontWeight: "bold" }}
          >
            {cartItem.product.name}
          </p>
          <p className="m-0 text-capitalize" style={{ fontSize: "12px" }}>
            {cartItem.product.description}
          </p>
        </div>
      </div>
      <div className="d-flex felx-row align-items-center">
        <p className="m-0 me-4">Quantity: {cartItem.quantity}</p>
        <div className="" style={{ width: "15vh" }}>
          <p className="m-0" style={{ fontSize: "18px", fontWeight: "bold" }}>
            ₹
            {(
              cartItem.product.price * cartItem.quantity -
              (cartItem.product.price * cartItem.quantity * 2) / 100
            ).toFixed(0)}
          </p>
          <p className="m-0">
            <span
              style={{
                textDecoration: "line-through",
                color: "rgb(132, 132, 132)",
              }}
            >
              ₹{(cartItem.product.price * cartItem.quantity).toFixed(0)}{" "}
            </span>
            <span style={{ color: "green" }}>
              {cartItem.product.discount}% off
            </span>
          </p>
        </div>
      </div>
    </div>
  );
};

export default PaymentItem;
