import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { UserState, fetchUserDetailsAfterLogin } from "./userapiSlice";

const initialState: UserState = {
  email: null,
  id: 0,
  name: null,
  username: null,
};

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(
        fetchUserDetailsAfterLogin.fulfilled,
        (state, action: PayloadAction<UserState>) => {
          state.email = action.payload.email;
          state.id = action.payload.id;
          state.name = action.payload.name;
          state.username = action.payload.username;
        }
      )
      .addCase("auth/logout", (state) => {
        state.email = null;
        state.id = null;
        state.name = null;
        state.username = null;
      });
  },
});

export default userSlice.reducer;
