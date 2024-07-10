import React, { useRef } from "react";
import Input from "../UI/Input";
import Form, { FormHandle } from "../UI/Form";
import {
  setShippingDetails,
  ShippingDetails,
} from "../../slice/shipppingSlice";
import Button from "../UI/Button";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";

const Shipping = () => {
  const form = useRef<FormHandle>(null);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  function onSubmit(data: unknown) {
    const extractData = data as ShippingDetails;
    console.log(extractData)
    dispatch(setShippingDetails(extractData));
    navigate("/payment", { replace: true });
  }
  return (
    <div
      className="d-flex justify-content-center align-items-center flex-column"
      style={{ width: "100vh", marginLeft: "150px" }}
    >
      <h1>Shipping</h1>
      <Form onSave={onSubmit} ref={form} style={{ width: "65%" }}>
        <div
          className="p-4 shadow d-flex flex-column justify-content-between"
          style={{ height: "40vh" }}
        >
          <h5>Contact Details</h5>
          <div className="d-flex flex-row justify-content-between">
            <div className="d-flex flex-column">
              <label htmlFor="firstName">FirstName</label>
              <Input
                className="rounded"
                style={{ border: "1px solid #D4D4D4" }}
                label={"firstName"}
                id={"firstName"}
                name="firstName"
              />
            </div>
            <div className="d-flex flex-column">
              <label htmlFor="lastName">LastName</label>
              <Input
                className="rounded"
                style={{ border: "1px solid #D4D4D4" }}
                label={"lastName"}
                id={"lastName"}
                name="lastName"
              />
            </div>
          </div>
          <div className="d-flex flex-column">
            <label>Email</label>
            <Input
              className="rounded"
              style={{ border: "1px solid #D4D4D4" }}
              label={"email"}
              id={"email"}
              name="email"
            />
          </div>
          <div className="d-flex flex-column">
            <label>Phone</label>
            <Input
              className="rounded"
              style={{ border: "1px solid #D4D4D4" }}
              label={"phone"}
              id={"phone"}
              name="phone"
            />
          </div>
        </div>
        <div
          className="mt-4 p-2 p-4 shadow d-flex flex-column justify-content-between"
          style={{ height: "50vh" }}
        >
          <h5>Shipping Details</h5>
          <div className="d-flex flex-column">
            <label>Flat/House no.</label>
            <Input
              className="rounded"
              style={{ border: "1px solid #D4D4D4" }}
              label={"flatNo"}
              id={"flatNo"}
              name="flatNo"
            />
          </div>
          <div className="d-flex flex-column">
            <label>Address</label>
            <Input
              className="rounded"
              style={{ border: "1px solid #D4D4D4" }}
              label={"address"}
              id={"addrss"}
              name="address"
            />
          </div>
          <div className="d-flex flex-row justify-content-between">
            <div className="d-flex flex-column">
              <label>City</label>
              <Input
                className="rounded"
                style={{ border: "1px solid #D4D4D4" }}
                label={"city"}
                id={"city"}
                name="city"
              />
            </div>
            <div className="d-flex flex-column">
              <label>State</label>
              <Input
                className="rounded"
                style={{ border: "1px solid #D4D4D4" }}
                label={"state"}
                id={"state"}
                name="state"
              />
            </div>
          </div>
          <div className="d-flex flex-row justify-content-between">
            <div className="d-flex flex-column">
              <label>Postal Code</label>
              <Input
                className="rounded"
                style={{ border: "1px solid #D4D4D4" }}
                label={"postalCode"}
                id={"postalCode"}
                name="postalCode"
              />
            </div>
            <div className="d-flex flex-column">
              <label>Famous Landmark</label>
              <Input
                className="rounded"
                style={{ border: "1px solid #D4D4D4" }}
                label={"famousLandmark"}
                id={"famousLandmark"}
                name="famousLandmark"
              />
            </div>
          </div>
        </div>
        <div
          className="mt-4 mb-5 "
          style={{ display: "flex", justifyContent: "flex-end" }}
        >
          <Button
            className="btn border-0 rounded-2 shadow"
            style={{
              padding: "10px 20px",
              background: "#6B72D6",
              color: "white",
            }}
            type="submit"
          >
            Continue
          </Button>
        </div>
      </Form>
    </div>
  );
};

export default Shipping;
