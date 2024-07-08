import React, { useRef } from "react";
import Form, { FormHandle } from "../UI/Form";
import Input from "../UI/Input";
import Button from "../UI/Button";
import "bootstrap/dist/css/bootstrap.css";
import { Link } from "react-router-dom";
import "../../css/input.css";
import { FaRegUser } from "react-icons/fa";
import { RiLockPasswordFill } from "react-icons/ri";
import backgroundImage from "../../images/bgImage.png";
import cartImage from "../../images/cart.png";
import { MdEmail } from "react-icons/md";
import { FaUserCircle } from "react-icons/fa";

import { useRegisterMutation } from "../../slice/authapiSlice";

export type RegisterProps = {
  name: String;
  username: String;
  email: String;
  password: String;
};

function RegisterForm() {
  const form = useRef<FormHandle>(null);
  const [register] = useRegisterMutation();

  async function onSubmit(data: unknown) {
    const extractData = data as RegisterProps;
    register(extractData);
  }

  return (
    <div className="container-fluid">
      <div className="row">
        <div
          className="col-sm"
          style={{ backgroundImage: `url(${backgroundImage})` }}
        >
          <div>
            <img src={cartImage} className="custom-image" alt="cart" />
          </div>
        </div>
        <Form onSave={onSubmit} ref={form} className="col-sm">
          <div className="text-center">
            <p className="h2 mb-4">Register</p>
            <div className="d-flex flex-column justify-content-center text-center">
              <div
                className="d-flex flex-direction-column p-2 rounded-pill mb-3"
                style={{ backgroundColor: "#F0EDFF", width: "350px" }}
              >
                <div className="pe-2 ps-2">
                  <FaRegUser size={18} />
                </div>
                <Input
                  label="name"
                  id="name"
                  type="text"
                  name="name"
                  className="border-0 flex-fill custom-input"
                  placeholder="Name"
                  style={{ backgroundColor: "#F0EDFF" }}
                  required
                />
              </div>
              <div
                className="d-flex flex-direction-column p-2 rounded-pill mb-3"
                style={{ backgroundColor: "#F0EDFF", width: "350px" }}
              >
                <div className="pe-2 ps-2">
                  <FaUserCircle size={18} />
                </div>
                <Input
                  label="username"
                  id="username"
                  type="text"
                  name="username"
                  className="border-0 flex-fill custom-input"
                  placeholder="Username"
                  style={{ backgroundColor: "#F0EDFF" }}
                  required
                />
              </div>
              <div
                className="d-flex flex-direction-column p-2 rounded-pill mb-3"
                style={{ backgroundColor: "#F0EDFF", width: "350px" }}
              >
                <div className="pe-2 ps-2">
                  <MdEmail size={18} />
                </div>
                <Input
                  label="email"
                  id="email"
                  type="email"
                  name="email"
                  className="border-0 flex-fill custom-input"
                  placeholder="Email"
                  style={{ backgroundColor: "#F0EDFF" }}
                  required
                />
              </div>
              <div
                className="d-flex flex-direction-column p-2 rounded-pill mb-3"
                style={{ backgroundColor: "#F0EDFF", width: "350px" }}
              >
                <div className="pe-2 ps-2">
                  <RiLockPasswordFill size={18} />
                </div>
                <Input
                  label="password"
                  id="password"
                  type="text"
                  name="password"
                  className="border-0 flex-fill custom-input"
                  placeholder="Password"
                  style={{ backgroundColor: "#F0EDFF" }}
                  required
                />
              </div>
            </div>

            <Button
              type="submit"
              className="btn custom-button text-white ps-4 pe-4 shadow rounded-pill"
            >
              REGISTER
            </Button>
            <p className="pt-4">
              Have already an account? <Link to="/login">Login</Link>
            </p>
          </div>
        </Form>
      </div>
    </div>
  );
}

export default RegisterForm;
