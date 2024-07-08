import { configureStore } from "@reduxjs/toolkit";

import { apiSlice } from "../slice/apiSlice";
import authSliceReducer from "../slice/authSlice";
import userSliceReducer from "../slice/userSlice";
import { useDispatch } from "react-redux";

export type RootSlice = {
  [apiSlice.reducerPath]: ReturnType<typeof apiSlice.reducer>;
  login: ReturnType<typeof authSliceReducer>;
  user: ReturnType<typeof userSliceReducer>;
};
const store = configureStore({
  reducer: {
    [apiSlice.reducerPath]: apiSlice.reducer,
    login: authSliceReducer,
    user: userSliceReducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({ thunk: true, serializableCheck: false }).concat(
      apiSlice.middleware
    ),

  devTools: true,
});

export type AppDispatch = typeof store.dispatch;

export const useAppDispatch = useDispatch.withTypes<AppDispatch>();

export default store;
