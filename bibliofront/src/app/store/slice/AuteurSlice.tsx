import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { defaultValue, IAuteur } from "../../model/AuteurModel";
import type { AppThunk, RootState } from "../store";

interface AuteurState {
  entity: IAuteur;
  entities: ReadonlyArray<IAuteur>;
  loading: boolean;
  updating: boolean;
  updateSuccess: boolean;
  errorMessage: string | undefined;
}

const initialState: Readonly<AuteurState> = {
  entity: defaultValue,
  entities: [],
  loading: false,
  updating: false,
  updateSuccess: false,
  errorMessage: undefined,
};

// Reducer

const auteurSlice = createSlice({
  name: "auteur",
  initialState,
  reducers: {
    requestGetEntityEntities: (state) => {
      state.loading = true;
      state.updateSuccess = false;
      state.errorMessage = undefined;
    },
    requestCreateUpdateDeleteEntity: (state) => {
      state.updating = true;
      state.updateSuccess = false;
      state.errorMessage = undefined;
    },
    failure: (state, action) => {
      state.loading = false;
      state.updating = false;
      state.updateSuccess = false;
      state.errorMessage = action.payload;
    },
    successGetEntity: (state, action) => {
      state.entity = action.payload.data;
      state.loading = false;
    },
    successGetEntities: (state, action) => {
      state.entities = action.payload.data;
      state.loading = false;
    },
    successCreateUpdateEntity: (state, action) => {
      state.entity = action.payload.data;
      state.updating = false;
      state.updateSuccess = true;
    },
    successDeleteEntity: (state) => {
      state.entity = defaultValue;
      state.updating = false;
      state.updateSuccess = true;
    },
    resetState: (state) => {
      state.entity = defaultValue;
      state.entities = [];
      state.loading = false;
      state.updating = false;
      state.updateSuccess = false;
      state.errorMessage = undefined;
    },
  },
});

export const baseUrl = "/auteur";

const {
  requestGetEntityEntities,
  requestCreateUpdateDeleteEntity,
  failure,
  successGetEntity,
  successGetEntities,
  successCreateUpdateEntity,
  successDeleteEntity,
  resetState,
} = auteurSlice.actions;

// Actions

export const getEntity =
  (id: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestGetEntityEntities);
    try {
      await axios.get<IAuteur>(`${baseUrl}/${id}`).then((response) => dispatch(successGetEntity(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const getEntityByLivreId =
  (id: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestGetEntityEntities);
    try {
      await axios.get<IAuteur>(`${baseUrl}/livre/${id}`).then((response) => dispatch(successGetEntity(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const getEntities = (): AppThunk => async (dispatch: any) => {
  dispatch(requestGetEntityEntities);
  try {
    await axios.get<IAuteur>(`${baseUrl}/all`).then((response) => dispatch(successGetEntities(response)));
  } catch (e) {
    dispatch(failure(e.message));
  }
};

export const createEntity =
  (entity: IAuteur): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestCreateUpdateDeleteEntity);
    try {
      const response = await axios.post<IAuteur>(`${baseUrl}`, entity);
      dispatch(successCreateUpdateEntity(response));
      await dispatch(getEntities());
      return response;
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const updateEntity =
  (id: number, entity: IAuteur): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestCreateUpdateDeleteEntity);
    try {
      await axios
        .put<IAuteur>(`${baseUrl}/${id}`, entity)
        .then((response) => dispatch(successCreateUpdateEntity(response)));
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const deleteEntity =
  (id: number): AppThunk =>
  async (dispatch: any) => {
    dispatch(requestCreateUpdateDeleteEntity);
    try {
      const response = await axios.delete<IAuteur>(`${baseUrl}`);
      dispatch(successDeleteEntity);
      await dispatch(getEntities());
      return response;
    } catch (e) {
      dispatch(failure(e.message));
    }
  };

export const reset = () => (dispatch: any) => {
  dispatch(resetState);
};

// Selectors

export const entity = (state: RootState) => state.auteur.entity;
export const entities = (state: RootState) => state.auteur.entities;
export const loading = (state: RootState) => state.auteur.loading;
export const updating = (state: RootState) => state.auteur.updating;
export const updateSuccess = (state: RootState) => state.auteur.updateSuccess;
export const errorMessage = (state: RootState) => state.auteur.errorMessage;

export default auteurSlice.reducer;
