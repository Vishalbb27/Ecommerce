import React, { useEffect, useState } from "react";
import { useGetCartByUserIdQuery } from "../../slice/cartApiSlice";
import CartItem from "./CartItem";
import { Link, useNavigate } from "react-router-dom";
import Button from "../UI/Button";
import { FaArrowRightLong } from "react-icons/fa6";

const Cart = () => {
  const { data, isLoading, isSuccess } = useGetCartByUserIdQuery(1);
  const [total, setTotal] = useState<number>();
  const [discount, setDiscount] = useState<number>();
  const navigate = useNavigate();

  useEffect(() => {
    if (!isLoading) {
      var totalPrice = 0;
      var totalDiscount = 0;
      if (data.cartDtos.length !== 0) {
        data.cartDtos.forEach(
          (cartItem) => (
            (totalPrice +=
              cartItem.product.price * cartItem.quantity -
              (cartItem.product.price * cartItem.quantity * 2) / 100),
            (totalDiscount +=
              (cartItem.product.price * cartItem.quantity * 2) / 100)
          )
        );
        setTotal(totalPrice);
        setDiscount(totalDiscount);
      }
    }
    console.log(total);
  }, [data, isLoading, total]);

    function handleCheckout(event: React.MouseEvent<HTMLButtonElement, MouseEvent>): void {
        navigate("/shipping")
    }

  return (
    <>
      {data && data.cartDtos.length === 0 ? (
        <div>
          <h3 style={{ textAlign: "center" }}>
            Your Cart is Empty. <Link to="/"> Add to Cart</Link>
          </h3>
        </div>
      ) : (
        <div className="d-flex flex-row">
          <div style={{ width: "90vh" }} className="me-5">
            {!isLoading &&
              data.cartDtos.map((cartItem) => <CartItem cartItem={cartItem} />)}
          </div>
          <div>
            <div className="ms-5 shadow-sm p-3">
              <p className="h4 mb-4">Price Details</p>

              <div className="d-flex flex-row justify-content-between mb-2">
                <p className="m-0" style={{ fontSize: "15px" }}>
                  Price
                </p>

                <p className="h5 m-0" style={{ fontSize: "15px" }}>
                  ₹ {discount && (discount + total).toFixed(0)}
                </p>
              </div>
              <div className="d-flex flex-row justify-content-between">
                <p style={{ fontSize: "15px" }}>Discount</p>

                <p
                  className="h5 text-success"
                  style={{ fontSize: "15px", fontWeight: "bold" }}
                >
                  - ₹{discount && discount.toFixed(0)}
                </p>
              </div>
              <p style={{ border: "1px dotted black", margin: "5px 0px" }}></p>
              <div className="d-flex flex-row justify-content-between">
                <p
                  style={{ fontSize: "20px", fontWeight: "bold", margin: "0" }}
                >
                  Total Amount
                </p>

                <p
                  style={{
                    fontSize: "20px",
                    fontWeight: "bold",
                    margin: "0px",
                  }}
                >
                  ₹ {total && total.toFixed(0)}
                </p>
              </div>
              <p style={{ border: "1px dotted black", margin: "5px 0px" }}></p>
              <p
                className="text-success"
                style={{ fontSize: "15px", margin: "10px 0px" }}
              >
                You will save ₹{discount && discount.toFixed(0)} on this order
              </p>
              <Button
                className="border-0 rounded shadow"
                style={{ width: "300px", background: "#4DE1C1" }}
                onClick={handleCheckout}
              >
                <div className="d-flex flex-row justify-content-between align-items-center text-white p-3">
                  <p className="m-0">₹ {total && total.toFixed(0)}</p>
                  <p className="m-0">
                    Checkout <FaArrowRightLong />
                  </p>
                </div>
              </Button>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default Cart;
