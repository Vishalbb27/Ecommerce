import React, { useEffect, useRef, useState } from "react";
import { useGetCartByUserIdQuery } from "../../slice/cartApiSlice";
import { Link, useNavigate } from "react-router-dom";
import Button from "../UI/Button";
import PaymentItem from "./PaymentItem";
import Form, { FormHandle } from "../UI/Form";
import Input from "../UI/Input";

const Payment = () => {
  const { data, isLoading } = useGetCartByUserIdQuery(1);
  const [total, setTotal] = useState<number>();
  const [discount, setDiscount] = useState<number>();
  const navigate = useNavigate();

  const form = useRef<FormHandle>(null);

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

  function handleSave(value: unknown): void {}

  function handleBack(
    event: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ): void {
    navigate("/", { replace: true });
  }

  function handlePay(
    event: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ): void {
    navigate("/confirmation", { replace: true });
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
              data.cartDtos.map((cartItem) => (
                <PaymentItem cartItem={cartItem} />
              ))}
          </div>
          <div className="ms-5 " style={{ width: "60vh" }}>
            <div className="shadow-sm p-3">
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
            </div>
            <div className="shadow-sm p-3 mt-4  mb-4">
              <p>Payment Methods</p>
              <Form onSave={handleSave} ref={form}>
                <div>
                  <Input
                    type="radio"
                    id="option1"
                    name="options"
                    value="option1"
                    label={""}
                  />
                  <label htmlFor="option1" className="ms-2">
                    Pay on Delivery
                  </label>
                  <p className="ms-4" style={{ fontSize: "12px" }}>
                    Pay with cash on delivery
                  </p>
                </div>
                <div>
                  <Input
                    type="radio"
                    id="option2"
                    name="options"
                    value="option2"
                    label={""}
                  />
                  <label htmlFor="option1" className="ms-2">
                    Credit/Debit Cards
                  </label>
                  <p className="ms-4" style={{ fontSize: "12px" }}>
                    Pay with your Credit / Debit Card
                  </p>
                </div>

                <div>
                  <Input
                    type="radio"
                    id="option4"
                    name="options"
                    value="option4"
                    label={""}
                    style={{ background: "#6B72D6" }}
                  />
                  <label htmlFor="option1" className="ms-2">
                    Other Payment Methods
                  </label>
                  <p className="ms-4" style={{ fontSize: "12px" }}>
                    Make payment through Gpay, Paypal, Paytm etc
                  </p>
                </div>
              </Form>
            </div>
            <div className="d-flex felx-row justify-content-between">
              <Button
                className="btn rounded-2 shadow"
                style={{
                  padding: "10px 25px",
                  background: "white",
                  color: "#6B72D6",
                  border: "1px solid #6B72D6",
                }}
                onClick={handleBack}
              >
                Back
              </Button>
              <Button
                className="btn border-0 rounded-2 shadow"
                style={{
                  padding: "10px 30px",
                  background: "#6B72D6",
                  color: "white",
                }}
                onClick={handlePay}
              >
                Pay
              </Button>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default Payment;
