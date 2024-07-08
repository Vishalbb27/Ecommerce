import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { LoginDetials } from "./authapiSlice";

const initialState: LoginDetials = {
  role: null,
  accessToken: null,
  tokenType: null,
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    setCredentials: (state, action: PayloadAction<LoginDetials>) => {
      state.accessToken = action.payload.accessToken;
      state.role = action.payload.role;
      state.tokenType = action.payload.tokenType;
    },
    logout: (state) => {
      state.accessToken = null;
      state.role = null;
      state.tokenType = null;
    },
  },
});

export const { setCredentials, logout } = authSlice.actions;
export default authSlice.reducer;
