<template>
  <section class="py-12 bg-white border-t-2 border-gray-300">
    <div class="container w-2/3 mx-auto">
      <!-- Back Button -->
      <div class="mb-6">
        <button
          class="flex items-center text-gray-500 hover:text-gray-700"
          @click="goBack"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            class="h-4 w-4 mr-1"
            fill="none"
            viewBox="0 0 24 24"
            stroke="currentColor"
          >
            <path
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M15 19l-7-7 7-7"
            />
          </svg>
          返回
        </button>
      </div>
      <div class="bg-white px-4 py-4">
        <!-- Header Section -->
        <div
          class="flex flex-col md:flex-row justify-between items-start md:items-center mb-6"
        >
          <div class="flex items-center mb-4 md:mb-0">
            <!-- Bank Logo -->
            <div class="mr-20">
              <img :src="bankicon" class="h-12 bank-logo-img" />
            </div>

            <!-- Product Name -->
            <div>
              <div class="flex items-center">
                <h1 class="text-xl font-medium mr-6">{{ bankname }}</h1>
                <div class="flex">
                  <span
                    class="bg-[#007029] text-white text-xs px-2 py-1 rounded mr-2"
                    >在线申请</span
                  >
                  <span
                    class="bg-[#007029] text-white text-xs px-2 py-1 rounded mr-2"
                    >特快审批</span
                  >
                  <span
                    class="bg-[#007029] text-white text-xs px-2 py-1 rounded"
                    >便捷</span
                  >
                </div>
              </div>
            </div>
          </div>
          <!-- Action Buttons -->
          <div class="flex space-x-3">
            <button
              class="bg-[#007029] text-white px-4 py-2 rounded-md text-sm hover:bg-green-700 transition-colors"
              @click="personalLoan()"
            >
              个人贷款
            </button>
            <button
              class="bg-[#007029] text-white px-4 py-2 rounded-md text-sm hover:bg-green-700 transition-colors"
              @click="combinationLoan()"
            >
              组合贷款
            </button>
          </div>
        </div>

        <!-- Product Description -->
        <div class="mb-8 text-sm leading-relaxed text-gray-700">
          <p>
            {{ bankintro }}
          </p>
        </div>
        <div class="border-t border-dashed border-gray-300 pt-4 mb-4"></div>
        <!-- Key Features -->
        <div class="grid grid-cols-4 gap-4 mb-10">
          <!-- Loan Amount -->
          <div class="text-left">
            <div class="flex items-baseline">
              <span class="text-[#007029] font-medium">最高</span>
              <span class="text-[#007029] text-3xl font-bold mx-1">{{
                bankmoney
              }}</span>
              <span class="text-[#007029]">元</span>
            </div>
            <div class="flex items-baseline text-sm text-gray-500 mt-1 ml-6">
              贷款额度
            </div>
          </div>

          <!-- Interest Rate -->
          <div class="text-center">
            <div class="flex items-baseline justify-center">
              <span class="text-[#007029] text-3xl font-bold"
                >{{ bankrate }}%</span
              >
              <span class="text-[#007029] text-sm">起</span>
            </div>
            <div class="text-sm text-gray-500 mt-1">产品利率</div>
          </div>

          <!-- Loan Term -->
          <div class="text-center">
            <div class="flex items-baseline justify-center">
              <span class="text-[#007029]">最长</span>
              <span class="text-[#007029] text-3xl font-bold mx-1">{{
                bankrepay
              }}</span>
              <span class="text-[#007029]">月</span>
            </div>
            <div class="text-sm text-gray-500 mt-1">贷款期限</div>
          </div>
        </div>

        <!-- Additional Features -->

        <div class="grid grid-cols-2 md:grid-cols-5 gap-4 mb-10">
          <div class="flex flex-col">
            <div class="text-sm text-gray-700">最快放款2-3日</div>
          </div>
          <div class="flex flex-col">
            <div class="text-sm text-gray-700">担保方式:信用</div>
          </div>
          <div class="flex flex-col">
            <div class="text-sm text-gray-700">还款方式:到期还本付息</div>
          </div>
          <div class="flex flex-col">
            <div class="text-sm text-gray-700">适用范围:覆盖全国</div>
          </div>
          <div class="flex flex-col">
            <div class="text-sm text-gray-700">所属机构:中国农业银行</div>
          </div>
        </div>
      </div>
      <!-- Product Characteristics -->
      <div class="bg-white px-4 py-4">
        <div class="mb-8">
          <h2
            class="text-lg font-medium mb-4 pb-2 border-b-2 border-[#007029] inline-block"
          >
            产品特色
          </h2>
          <p class="text-sm leading-relaxed text-gray-700">
            {{ bankintro }}
          </p>
        </div>

        <!-- Eligibility Requirements -->
        <div class="mb-8">
          <h2
            class="text-lg font-medium mb-4 pb-2 border-b-2 border-[#007029] inline-block"
          >
            准入条件
          </h2>
          <p class="text-sm leading-relaxed text-gray-700">
            年龄要求：18-65周岁（学生贷、养老金贷等产品可能有特殊年龄限制）
          </p>
          <p class="text-sm leading-relaxed text-gray-700">
            身份证明：有效身份证、护照+居留许可
          </p>
          <p class="text-sm leading-relaxed text-gray-700">
            民事行为能力：无刑事犯罪记录、非失信被执行人
          </p>
        </div>

        <!-- Application Materials -->
        <div class="mb-8">
          <h2
            class="text-lg font-medium mb-4 pb-2 border-b-2 border-[#007029] inline-block"
          >
            申请所需材料
          </h2>
          <p class="text-sm leading-relaxed text-gray-700 mb-4">
            征信记录、收入证明、负债比例
          </p>
        </div>
      </div>
    </div>
    <!-- Loan Application Modal -->
    <div
      v-if="showLoanModal"
      class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50"
    >
      <div class="bg-white rounded-lg w-1/2 max-w-2xl">
        <div class="p-4 border-b">
          <h3 class="text-lg font-bold">个人贷款申请</h3>
        </div>
        <div class="p-6 grid grid-cols-3 gap-4">
          <div class="flex flex-col">
            <label class="text-gray-500 text-sm mb-1">姓名</label>
            <input v-model="loanForm.name" class="border rounded p-2" />
          </div>
          <div class="flex flex-col">
            <label class="text-gray-500 text-sm mb-1">贷款额度（万元）</label>
            <input v-model="loanForm.amount" class="border rounded p-2" />
          </div>
          <div class="flex flex-col">
            <label class="text-gray-500 text-sm mb-1">贷款利率</label>
            <input v-model="loanForm.rate" class="border rounded p-2" />
          </div>
          <div class="flex flex-col">
            <label class="text-gray-500 text-sm mb-1">贷款周期（月）</label>
            <input v-model="loanForm.term" class="border rounded p-2" />
          </div>
          <div class="flex flex-col">
            <label class="text-gray-500 text-sm mb-1">联系方式</label>
            <input v-model="loanForm.contact" class="border rounded p-2" />
          </div>
          <div class="flex flex-col">
            <label class="text-gray-500 text-sm mb-1">身份证号</label>
            <input v-model="loanForm.idNumber" class="border rounded p-2" />
          </div>
          <div class="col-span-2">
            <label class="text-gray-500 text-sm mb-1">上传资料</label>
            <input
              type="file"
              multiple
              @change="handleFileUpload"
              class="border rounded p-2 w-full"
              accept="image/*"
            />
            <div v-if="Allfile.length > 0" class="mt-2">
              <div class="text-xs text-gray-500 mb-1">已选择文件:</div>
              <div class="flex flex-wrap gap-2">
                <span
                  v-for="(file, index) in Allfile"
                  :key="index"
                  class="bg-gray-100 px-2 py-1 rounded text-xs"
                >
                  {{ file.name }}
                </span>
              </div>
            </div>
          </div>
        </div>
        <div class="p-4 border-t flex justify-end space-x-4">
          <button
            class="px-4 py-2 text-gray-500 hover:text-gray-700"
            @click="showLoanModal = false;Allfile=''"
          >
            取消
          </button>
          <button
            class="px-4 py-2 bg-[#007029] text-white rounded hover:bg-green-600"
            @click="submitLoanApplication"
          >
            申请
          </button>
        </div>
      </div>
    </div>

    <!-- Combination Loan Modal -->
    <div
      v-if="showCombinationModal"
      class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50"
    >
      <div class="bg-white rounded-lg w-1/2 max-w-2xl">
        <div class="p-4 border-b">
          <h3 class="text-lg font-bold">组合贷款申请</h3>
        </div>
        <div class="p-6 grid grid-cols-3 gap-4">
          <div class="flex flex-col">
            <label class="text-gray-500 text-sm mb-1">贷款额度（万元）</label>
            <input v-model="combinationForm.amount" class="border rounded p-2" />
          </div>
          <div class="flex flex-col">
            <label class="text-gray-500 text-sm mb-1">贷款利率</label>
            <input v-model="combinationForm.rate" class="border rounded p-2" />
          </div>
          <div class="flex flex-col">
            <label class="text-gray-500 text-sm mb-1">贷款周期（月）</label>
            <input v-model="combinationForm.term" class="border rounded p-2" />
          </div>

          <div class="col-span-3">
            <h4 class="text-md font-medium mb-2">贷款人1</h4>
            <div class="grid grid-cols-3 gap-4 mb-4">
              <div class="flex flex-col">
                <label class="text-gray-500 text-sm mb-1">姓名</label>
                <input v-model="combinationForm.realName" class="border rounded p-2" />
              </div>
              <div class="flex flex-col">
                <label class="text-gray-500 text-sm mb-1">联系方式</label>
                <input v-model="combinationForm.phone" class="border rounded p-2" />
              </div>
              <div class="flex flex-col">
                <label class="text-gray-500 text-sm mb-1">身份证号</label>
                <input v-model="combinationForm.idNum" class="border rounded p-2" />
              </div>
            </div>

            <h4 class="text-md font-medium mb-2">贷款人2</h4>
            <div class="grid grid-cols-3 gap-4 mb-4">
              <div class="flex flex-col">
                <label class="text-gray-500 text-sm mb-1">姓名</label>
                <input v-model="combinationForm.combinationName1" class="border rounded p-2" />
              </div>
              <div class="flex flex-col">
                <label class="text-gray-500 text-sm mb-1">联系方式</label>
                <input v-model="combinationForm.combinationPhone1" class="border rounded p-2" />
              </div>
              <div class="flex flex-col">
                <label class="text-gray-500 text-sm mb-1">身份证号</label>
                <input v-model="combinationForm.combinationIdnum1" class="border rounded p-2" />
              </div>
            </div>

            <h4 class="text-md font-medium mb-2">贷款人3</h4>
            <div class="grid grid-cols-3 gap-4 mb-4">
              <div class="flex flex-col">
                <label class="text-gray-500 text-sm mb-1">姓名</label>
                <input v-model="combinationForm.combinationName2" class="border rounded p-2" />
              </div>
              <div class="flex flex-col">
                <label class="text-gray-500 text-sm mb-1">联系方式</label>
                <input v-model="combinationForm.combinationPhone2" class="border rounded p-2" />
              </div>
              <div class="flex flex-col">
                <label class="text-gray-500 text-sm mb-1">身份证号</label>
                <input v-model="combinationForm.combinationIdnum2" class="border rounded p-2" />
              </div>
            </div>
          </div>

          <div class="col-span-2">
            <label class="text-gray-500 text-sm mb-1">上传资料</label>
            <input
              type="file"
              multiple
              @change="handleFileUpload"
              class="border rounded p-2 w-full"
              accept="image/*"
            />
            <div v-if="Allfile.length > 0" class="mt-2">
              <div class="text-xs text-gray-500 mb-1">已选择文件:</div>
              <div class="flex flex-wrap gap-2">
                <span
                  v-for="(file, index) in Allfile"
                  :key="index"
                  class="bg-gray-100 px-2 py-1 rounded text-xs"
                >
                  {{ file.name }}
                </span>
              </div>
            </div>
          </div>
        </div>
        <div class="p-4 border-t flex justify-end space-x-4">
          <button
            class="px-4 py-2 text-gray-500 hover:text-gray-700"
            @click="showCombinationModal = false;Allfile=''"
          >
            取消
          </button>
          <button
            class="px-4 py-2 bg-[#007029] text-white rounded hover:bg-green-600"
            @click="submitCombinationLoan"
          >
            申请
          </button>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import { useRoute, useRouter } from "vue-router";
import { ref, reactive, onMounted } from "vue";
import { ElMessage } from "element-plus";
import { apiClient } from "../api/apiService.js";
import { useStore } from "vuex";
import { ElForm, ElFormItem, ElInput } from "element-plus"; // 注意：ElMessage 重复导入已移除
import { validatePhone, validateIdCard, validatePositiveNumber } from "@/utils/validator";
import bank01 from "@/assets/img/zgnyyh.png";
import bank02 from "@/assets/img/zggsyh.png";
import bank03 from "@/assets/img/zgjsyh.png";
import bank04 from "@/assets/img/zgnyyh.png";
import bank05 from "@/assets/img/zgnyyh.png";
import bank06 from "@/assets/img/zgnyyh.png";
import bank07 from "@/assets/img/zgnyyh.png";
import bank08 from "@/assets/img/zgnyyh.png";
import bank09 from "@/assets/img/zgnyyh.png";
import bank10 from "@/assets/img/zgnyyh.png";
import bank11 from "@/assets/img/zgnyyh.png";

const route = useRoute();
const router = useRouter();
const store = useStore();

let bankId = ref();
let bankicon = ref();
let bankname = ref();
let bankintro = ref();
let bankmoney = ref();
let bankrate = ref();
let bankrepay = ref();

onMounted(async () => {
  bankId.value = route.query.bankId;
  bankicon.value = route.query.bankicon;
  bankname.value = route.query.bankname;
  bankintro.value = route.query.bankintro;
  bankmoney.value = route.query.bankmoney;
  bankrate.value = route.query.bankrate;
  bankrepay.value = route.query.bankrepay;
});

const goBack = () => {
  router.back();
};
const showLoanModal = ref(false);
const combinationFlag = ref(false);
const showCombinationModal = ref(false);

const loanForm = ref({
  name: '',
  amount: '',
  rate: '',
  term: '',
  contact: '',
  idNumber: '',
  files: []
});
const combinationForm = ref({
  amount: '',
  rate: '',
  term: '',
  realName: '',
  phone: '',
  idNum: '',
  combinationName1: '',
  combinationPhone1: '',
  combinationIdnum1: '',
  combinationName2: '',
  combinationPhone2: '',
  combinationIdnum2: '',
  files: []
});
const fileInfo = ref('');
const Allfile = ref([]);
const DBfile = ref([]);

const personalLoan = () => {
  if (!window.localStorage.token) {
    ElMessage.error("您未登录，请先登录");
  } else {
    showLoanModal.value = true;
  }
};
const combinationLoan = () => {
  if (!window.localStorage.token) {
    ElMessage.error("您未登录，请先登录");
  } else {
    showCombinationModal.value = true;
  }
};

const uploadLoading = ref(false);

const handleFileUpload = async (event) => {
  const files = Array.from(event.target.files);
  if (files.length === 0) {
    fileInfo.value = '';
    return;
  }
  Allfile.value = files;

  uploadLoading.value = true;

  try {
    const uploadPromises = files.map(async (file) => {
      const formData = new FormData();
      formData.append("file", file);

      const response = await apiClient.post(
          `${store.state.fileUploadRoad}/file/upload/info`,
          formData,
          {
            headers: {
              Authorization: window.localStorage.token,
              "Content-Type": "multipart/form-data",
            },
          }
      );

      if (response.flag) {
        DBfile.value.push(response.data.split('/')[1]);
      } else {
        throw new Error(response.message);
      }
    });

    const uploadedFiles = await Promise.all(uploadPromises);
    ElMessage.success("文件上传成功");
    return uploadedFiles;
  } catch (error) {
    ElMessage.error(`文件上传失败: ${error.message}`);
    return [];
  } finally {
    uploadLoading.value = false;
  }
};

// 个人贷款提交
const submitLoanApplication = async () => {
  try {
    await loanFormRef.value.validate();
  } catch (error) {
    ElMessage.error("表单填写有误，请检查后重试！");
    return;
  }

  if (
      !loanForm.value.name ||
      !loanForm.value.amount ||
      !loanForm.value.rate ||
      !loanForm.value.term ||
      !loanForm.value.contact ||
      !loanForm.value.idNumber
  ) {
    ElMessage.error("申请信息必须完整");
    return;
  }

  fileInfo.value = DBfile.value.join(';');

  if (!DBfile) {
    ElMessage.error("请上传至少一个文件");
    return;
  }

  const param = ref({
    bankId: bankId.value,
    realName: loanForm.value.name,
    money: loanForm.value.amount,
    rate: loanForm.value.rate,
    repayment: loanForm.value.term,
    phone: loanForm.value.contact,
    idNum: loanForm.value.idNumber,
    fileInfo: fileInfo.value,
  });

  try {
    const response = await apiClient.post(`/finance/add`, param.value, {
      headers: {
        Authorization: window.localStorage.token,
      },
    });
    if (response.flag) {
      ElMessage.success("贷款申请已提交");
      showLoanModal.value = false;
      loanForm.value = {
        name: "",
        amount: "",
        rate: "",
        term: "",
        contact: "",
        idNumber: "",
        files: [],
      };
      fileInfo.value = '';
      Allfile.value = [];
      DBfile.value = [];
    } else {
      ElMessage.error("贷款申请提交失败，请重新登录进行提交");
    }
  } catch (e) {
    ElMessage.error("贷款申请提交失败，请正确填写信息或重新登录进行申请");
  }
};

// 组合贷款提交
const submitCombinationLoan = async () => {
  try {
    await combinationFormRef.value.validate();
  } catch (error) {
    ElMessage.error("表单填写有误，请检查后重试！");
    return;
  }

  if (
      !combinationForm.value.amount ||
      !combinationForm.value.rate ||
      !combinationForm.value.term ||
      !combinationForm.value.realName ||
      !combinationForm.value.phone ||
      !combinationForm.value.idNum ||
      !combinationForm.value.combinationName1 ||
      !combinationForm.value.combinationPhone1 ||
      !combinationForm.value.combinationIdnum1 ||
      !combinationForm.value.combinationName2 ||
      !combinationForm.value.combinationPhone2 ||
      !combinationForm.value.combinationIdnum2
  ) {
    ElMessage.error("请填写完整信息");
    return;
  }

  fileInfo.value = DBfile.value.join(';');

  if (!DBfile) {
    ElMessage.error("请上传至少一个文件");
    return;
  }

  const param = {
    bankId: bankId.value,
    money: combinationForm.value.amount,
    rate: combinationForm.value.rate,
    repayment: combinationForm.value.term,
    realName: combinationForm.value.realName,
    phone: combinationForm.value.phone,
    idNum: combinationForm.value.idNum,
    combinationName1: combinationForm.value.combinationName1,
    combinationPhone1: combinationForm.value.combinationPhone1,
    combinationIdnum1: combinationForm.value.combinationIdnum1,
    combinationName2: combinationForm.value.combinationName2,
    combinationPhone2: combinationForm.value.combinationPhone2,
    combinationIdnum2: combinationForm.value.combinationIdnum2,
    fileInfo: fileInfo.value
  };

  try {
    const response = await apiClient.post(`/finance/addFinanceMulti`, param, {
      headers: {
        Authorization: window.localStorage.token,
      },
    });

    if (response.flag) {
      ElMessage.success("组合贷款申请已提交");
      showCombinationModal.value = false;
      combinationForm.value = {
        amount: '',
        rate: '',
        term: '',
        realName: '',
        phone: '',
        idNum: '',
        combinationName1: '',
        combinationPhone1: '',
        combinationIdnum1: '',
        combinationName2: '',
        combinationPhone2: '',
        combinationIdnum2: '',
        files: []
      };
      fileInfo.value = '';
      Allfile.value = [];
      DBfile.value = [];
    } else {
      ElMessage.error(response.message);
    }
  } catch (error) {  // 补充了缺失的catch块
    ElMessage.error(`提交失败: ${error.message}`);
  }
};

// 个人贷款表单验证规则
const loanValidationRules = reactive({
  name: [
    { required: true, message: "请输入姓名", trigger: "blur" },
    { min: 2, max: 10, message: "姓名长度在2-10个字符之间", trigger: "blur" }
  ],
  amount: [
    { required: true, message: "请输入贷款金额", trigger: "blur" },
    { validator: validatePositiveNumber, message: "贷款金额必须为正数且最多保留2位小数", trigger: "blur" }
  ],
  rate: [
    { required: true, message: "请输入贷款利率", trigger: "blur" },
    { type: "number", min: 0, max: 30, message: "利率范围为0-30%", trigger: "blur" }
  ],
  term: [
    { required: true, message: "请输入贷款周期", trigger: "blur" },
    { type: "number", min: 1, max: 360, message: "贷款周期为1-360个月", trigger: "blur" }
  ],
  contact: [
    { required: true, message: "请输入联系方式", trigger: "blur" },
    { validator: validatePhone, message: "手机号格式不正确", trigger: "blur" }
  ],
  idNumber: [
    { required: true, message: "请输入身份证号", trigger: "blur" },
    { validator: validateIdCard, message: "身份证号格式不正确", trigger: "blur" }
  ]
});

// 组合贷款表单验证规则
const combinationValidationRules = reactive({
  realName: loanValidationRules.name,
  phone: loanValidationRules.contact,
  idNum: loanValidationRules.idNumber,
  amount: loanValidationRules.amount,
  rate: loanValidationRules.rate,
  term: loanValidationRules.term,
  combinationName1: loanValidationRules.name,
  combinationPhone1: loanValidationRules.contact,
  combinationIdnum1: loanValidationRules.idNumber,
  combinationName2: loanValidationRules.name,
  combinationPhone2: loanValidationRules.contact,
  combinationIdnum2: loanValidationRules.idNumber
});

// 表单引用
const loanFormRef = ref(null);
const combinationFormRef = ref(null);
</script>
<style scoped>
@import "@/assets/form-validation.css";
/* 其他样式 */
</style>