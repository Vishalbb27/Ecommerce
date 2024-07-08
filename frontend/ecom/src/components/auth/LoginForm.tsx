import React, { useEffect, useRef } from "react";
import Form, { FormHandle } from "../UI/Form";
import Input from "../UI/Input";
import Button from "../UI/Button";

import "bootstrap/dist/css/bootstrap.css";

import { Link, useNavigate } from "react-router-dom";
import { useLoginMutation } from "../../slice/authapiSlice";
import { setCredentials } from "../../slice/authSlice";
import { RootSlice, useAppDispatch } from "../../store/store";
import { fetchUserDetailsAfterLogin } from "../../slice/userapiSlice";
import { useSelector } from "react-redux";
import { toast } from "react-toastify";
import { FaUserCircle } from "react-icons/fa";
import "../../css/input.css";
import { RiLockPasswordFill } from "react-icons/ri";
import backgroundImage from "../../images/bgImage.png";
import cartImage from "../../images/cart.png"

export type Login = {
  name: String;
  password: String;
};
function LoginForm() {
  const data = useSelector((state: RootSlice) => state.login);
  const navigate = useNavigate();
  const dispatch = useAppDispatch();
  const form = useRef<FormHandle>(null);

  useEffect(() => {
    if (data.accessToken !== null) {
      navigate("/");
    }
  }, [navigate, data]);
  const [login, { isLoading, isError }] = useLoginMutation();

  async function onSubmit(data: unknown) {
    const extractData = data as Login;

    try {
      const res = await toast.promise(
        login({
          name: extractData.name,
          password: extractData.password,
        }).unwrap(),
        {
          success: "Login Successful!!",
          error: "Username or password does not exist!!",
          pending: "Loading.....",
        }
      );
      dispatch(setCredentials({ ...res }));
      if (!isLoading && !isError) {
        dispatch(fetchUserDetailsAfterLogin(extractData.name));
      }
    } catch (error) {
      console.log(error);
    }
  }

  return (
    <div className="container-fluid">
      <div className="row">
        <Form
          onSave={onSubmit}
          ref={form}
          className="col-sm"
          // style={{ width: "400px" }}
        >
          <div className="text-center">
            <p className="h2 mb-4">Login</p>
            <div className="d-flex flex-column justify-content-center text-center">
              <div
                className="d-flex flex-direction-column p-2 rounded-pill mb-3"
                style={{ backgroundColor: "#F0EDFF", width: "350px" }}
              >
                <div className="pe-2 ps-2">
                  <FaUserCircle size={18} />
                </div>

                <Input
                  label="name"
                  id="name"
                  type="text"
                  name="name"
                  className="border-0 flex-fill custom-input"
                  placeholder="Username"
                  style={{ backgroundColor: "#F0EDFF" }}
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
                  type="password"
                  name="password"
                  className="border-0 flex-fill custom-input"
                  placeholder="Password"
                  style={{ backgroundColor: "#F0EDFF" }}
                />
              </div>
            </div>

            <Button
              type="submit"
              className="btn custom-button text-white ps-4 pe-4 shadow rounded-pill"
            >
              LOGIN
            </Button>
            <p className="pt-4">
              Not registered yet? <Link to="/register" className="text-primary">Register</Link>
            </p>
          </div>
        </Form>
        <div
          className="col-sm"
          style={{ backgroundImage: `url(${backgroundImage})` }}
        >
          <div>
            <img src={cartImage} className="custom-image" alt="cart" />
          </div>
        </div>
      </div>
    </div>
  );
}

export default LoginForm;
