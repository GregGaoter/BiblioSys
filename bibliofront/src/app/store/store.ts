import { Action, configureStore, ThunkAction } from "@reduxjs/toolkit";
import bibliothequeReducer from "../store/slice/BibliothequeSlice";
import livreReducer from "../store/slice/LivreSlice";
import rayonReducer from "../store/slice/RayonSlice";
import genreReducer from "../store/slice/GenreSlice";

export const store = configureStore({
  reducer: {
    bibliotheque: bibliothequeReducer,
    livre: livreReducer,
    rayon: rayonReducer,
    genre: genreReducer,
  },
  middleware: (getDefaultMiddleware) => getDefaultMiddleware({ serializableCheck: false }),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
export type AppThunk = ThunkAction<void, RootState, unknown, Action>;
