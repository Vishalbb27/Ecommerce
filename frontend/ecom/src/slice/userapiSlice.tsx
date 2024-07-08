import { createAsyncThunk } from "@reduxjs/toolkit";
import { AUTH_URL } from "../constants/constants";
import { apiSlice } from "./apiSlice";

export type UserState = {
  name: String | null;
  email: String | null;
  username: String | null;
  id: number | 0;
};

export const fetchUserDetailsAfterLogin = createAsyncThunk(
  "user/fetchUserDetailsAfterLogin",
  async (username: String, thunkAPI) => {
    const dispatch = thunkAPI.dispatch;
    try {
      const response = await dispatch(
        userapiSlice.endpoints.getUser.initiate(username)
      );
      return response.data;
    } catch (error) {
      return thunkAPI.rejectWithValue(error);
    }
  }
);

const userapiSlice = apiSlice.injectEndpoints({
  endpoints: (builder) => ({
    getUser: builder.query<UserState, String>({
      query: (username) => ({
        url: `${AUTH_URL}/user/${username}`,
        method: "GET",
      }),
    }),
    getUsers: builder.query<UserState[], void>({
      query: () => ({
        url: `/users`,
        method: "GET",
      }),
    }),
  }),
});

export const { useGetUserQuery, useGetUsersQuery } = userapiSlice;
