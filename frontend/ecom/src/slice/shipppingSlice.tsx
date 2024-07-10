import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { stat } from "fs";

export type ShippingDetails = {
  firstName: string;
  lastName: string;
  email: string;
  number: string;
  flatNo: string;
  address: string;
  city: string;
  state: string;
  postalCode: string;
  landmark: string;
};

const initialState: ShippingDetails = {
  firstName: null,
  lastName: null,
  email: null,
  number: null,
  flatNo: null,
  address: null,
  city: null,
  state: null,
  postalCode: null,
  landmark: null,
};

const shippingSlice = createSlice({
  name: "shipping",
  initialState,
  reducers: {
    setShippingDetails: (state, action: PayloadAction<ShippingDetails>) => {
      state.firstName = action.payload.firstName;
      state.lastName = action.payload.lastName;
      state.email = action.payload.email;
      state.number = action.payload.number;
      state.flatNo = action.payload.flatNo;
      state.address = action.payload.address;
      state.city = action.payload.city;
      state.state = action.payload.state;
      state.postalCode = action.payload.postalCode;
      state.landmark = action.payload.landmark;
    },
  },
});

export const { setShippingDetails } = shippingSlice.actions;

export default shippingSlice.reducer;
