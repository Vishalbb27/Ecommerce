import React from "react";
import { MdDone } from "react-icons/md";
import confirmationImage from "../../images/confirmation.png";
import Button from "../UI/Button";
import { useNavigate } from "react-router-dom";
const Confirmation = () => {
  const navigate = useNavigate();
  function handleHome(
    event: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ): void {
    navigate("/", { replace: true });
  }

  return (
    <div className="d-flex flex-row ">
      <div
        className="d-flex justify-content-between flex-column shadow rounded-5"
        style={{ padding: "10px 30px", width: "450px" }}
      >
        <div className="d-flex justify-content-center">
          <div
            style={{
              background: "#B5FDC9",
              padding: "20px",
              borderRadius: "100%",
              width: "100px",
            }}
          >
            <div
              style={{
                background: "#00E540",
                borderRadius: "100%",
                padding: "10px",
              }}
            >
              <MdDone color="white" size={40} />
            </div>
          </div>
        </div>
        <div className="d-flex flex-column align-items-center ">
          <h2>Payment Successful</h2>
          <h2>₹3922.00</h2>
        </div>
        <p style={{ border: "1px solid black", margin: "5px 0px" }}></p>
        <div className="d-flex flex-column justify-content-between">
          <div className="d-flex flex-row justify-content-between">
            <p>Payment Time</p>
            <p>09 -04-2023, 02.21 AM</p>
          </div>
          <div className="d-flex flex-row justify-content-between">
            <p>Sender Name</p>
            <p>Jack</p>
          </div>
        </div>
        <p style={{ border: "1px dotted black", margin: "5px 0px" }}></p>
        <div>
          <div className="d-flex flex-row justify-content-between">
            <p>Total Payment</p>
            <p>₹3922</p>
          </div>
        </div>
      </div>
      <div className="ms-5 d-flex flex-column">
        <div>
          <img
            src={confirmationImage}
            alt="confirmation"
            style={{ height: "60vh", width: "auto" }}
          />
        </div>
        <p className="text-center">
          Thank you for shopping, continue shopping?
        </p>
        <Button
          onClick={handleHome}
          className="shadow"
          style={{
            margin: "10px 70px",
            border: "0",
            background: "#FFD60A",
            padding: "10px 20px",
            fontWeight: "bold",
            borderRadius: "20px",
          }}
        >
          Back To Home
        </Button>
      </div>
    </div>
  );
};

export default Confirmation;
