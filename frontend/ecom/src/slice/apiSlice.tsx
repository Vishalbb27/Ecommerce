import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { BASE_URL } from "../constants/constants";
import { RootSlice } from "../store/store";

const baseQuery = fetchBaseQuery({
  baseUrl: BASE_URL,
  prepareHeaders: async (headers, { getState }) => {
    const token = (getState() as RootSlice).login.accessToken;
    if (token) {
      headers.set("Authorization", `Bearer ${token}`);
    }
  },
});

export const apiSlice = createApi({
  baseQuery,
  tagTypes: ["Task", "UserTask"],
  endpoints: (builder) => ({}),
});
