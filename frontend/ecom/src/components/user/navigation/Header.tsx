import React, { FormEvent, useRef, useState } from "react";
import Form, { FormHandle } from "../../UI/Form";
import Input from "../../UI/Input";
import { CiSearch } from "react-icons/ci";
import Select from "../../UI/Select";
import { useGetCategoriesQuery } from "../../../slice/categoryApiSlice";
import Button from "../../UI/Button";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { useLazyLogoutQuery } from "../../../slice/authapiSlice";
import { RootSlice } from "../../../store/store";
import { toast } from "react-toastify";
import { logout } from "../../../slice/authSlice";

const Header = () => {
  const { data } = useGetCategoriesQuery();
  const form = useRef<FormHandle>(null);
  async function onSubmit(data: unknown) {
    const extractData = data as String;
    console.log(extractData);
  }
  const [selectedValue, setSelectedValue] = useState<string>("");

  const handleChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedValue(event.currentTarget.value); // Update state with the selected value
  };

  const { role } = useSelector((state: RootSlice) => state.login);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [logoutQuery] = useLazyLogoutQuery();
  function handleLogout(event: FormEvent<HTMLButtonElement>): void {
    logoutQuery();
    dispatch(logout());
    toast.success("LOGOUT Successful!!!");
    navigate("/login");
  }
  return (
    <div className="d-flex flex-row shadow-sm p-2 ps-5 pe-5 justify-content-between">
      <p className="h4">ECOM</p>
      <Form onSave={onSubmit} ref={form}>
        <div
          className="d-flex flex-direction-column p-2 shadow-sm "
          style={{ width: "250px" }}
        >
          <div className="pe-2 ps-2">
            <CiSearch size={18} />
          </div>
          <Input
            label="search"
            id="search"
            type="text"
            name="search"
            className="border-0 flex-fill custom-input"
            placeholder="Search..."
            required
          />
        </div>
      </Form>
      <div className="d-flex flex-row justify-content-center">
        <p className="pt-2 h6">Filter: &nbsp;</p>
        {data && (
          <Select
            label=" "
            id="filter"
            options={data}
            className="bg-warning text-dark border-0 rounded-pill p-2 ps-3 pe-3 text-align-center"
            onChange={handleChange}
            optionName="Category"
          />
        )}
      </div>
      <div className="d-flex flex-row justify-content-center">
        <p className="pt-2 h6">Sort By: &nbsp;</p>
        {data && (
          <Select
            label=" "
            id="filter"
            options={data}
            className="bg-warning text-dark border-0 rounded-pill p-2 ps-3 pe-3"
            onChange={handleChange}
            optionName="Price"
          />
        )}
      </div>
      <div className="">
        <Button
          className=" btn border-0 p-2 btn-dark text-white rounded-3"
          onClick={handleLogout}
        >
          LOGOUT
        </Button>
      </div>
    </div>
  );
};

export default Header;
