window.swaggerSpec={
  "openapi" : "3.0.1",
  "info" : {
    "title" : "PETRA API",
    "description" : "PETRA API description",
    "version" : "0.1.0"
  },
  "servers" : [ {
    "url" : "http://15.165.93.55:8080"
  } ],
  "tags" : [ ],
  "paths" : {
    "/login" : {
      "post" : {
        "tags" : [ "login" ],
        "summary" : "로그인 후 쿠키 발급",
        "description" : "로그인 후 쿠키를 발급받을 수 있습니다.",
        "operationId" : "LogIn and Get Cookie",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/login486549215"
              },
              "examples" : {
                "LogIn and Get Cookie" : {
                  "value" : "{\"id\":\"heather\",\"name\":\"������\"}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/login-1664081379"
                },
                "examples" : {
                  "LogIn and Get Cookie" : {
                    "value" : "{\r\n  \"code\" : 2000,\r\n  \"message\" : \"성공\"\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/mypage" : {
      "get" : {
        "tags" : [ "mypage" ],
        "summary" : "mypage조회",
        "description" : "mypage에서 사용자, 스케줄 개수, 리뷰 개수를 조회합니다.",
        "operationId" : "마이페이지 조회",
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/mypage2104152416"
                },
                "examples" : {
                  "마이페이지 조회" : {
                    "value" : "{\r\n  \"code\" : 2000,\r\n  \"message\" : \"성공\",\r\n  \"data\" : {\r\n    \"userId\" : \"park1\",\r\n    \"myScheduleCount\" : 16,\r\n    \"myReviewCount\" : 0\r\n  }\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/schedule/{scheduleId}" : {
      "get" : {
        "tags" : [ "schedule" ],
        "summary" : "개별 스케줄 조회",
        "description" : "스케줄 idx로 스케줄에 대한 상세정보를 조회합니다.",
        "operationId" : "개별 스케줄 조회",
        "parameters" : [ {
          "name" : "scheduleId",
          "in" : "path",
          "description" : "조회를 원하는 스케줄 idx",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/schedule-scheduleId1469518433"
                },
                "examples" : {
                  "개별 스케줄 조회" : {
                    "value" : "{\r\n  \"code\" : 2000,\r\n  \"message\" : \"성공\",\r\n  \"data\" : {\r\n    \"id\" : 1,\r\n    \"title\" : \"1박 2일 강릉 여행\",\r\n    \"adult\" : 0,\r\n    \"child\" : 0,\r\n    \"startDate\" : \"2022-05-10\",\r\n    \"endDate\" : \"2022-05-10\",\r\n    \"planList\" : [ {\r\n      \"id\" : 1,\r\n      \"memo\" : \"밥부터 먹자\",\r\n      \"order\" : 1,\r\n      \"startDate\" : \"2022-04-08\",\r\n      \"endDate\" : \"2022-04-08\",\r\n      \"attraction\" : {\r\n        \"id\" : 1,\r\n        \"name\" : \"강릉초당순두부\",\r\n        \"address\" : \"강릉시 123-1\",\r\n        \"category\" : \"식당\"\r\n      }\r\n    } ]\r\n  }\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/schedules/plans/copy" : {
      "post" : {
        "tags" : [ "schedules" ],
        "summary" : "기존 스케줄의 Plan -> 다른 (기존 or 신규생성된 )스케줄에 복사",
        "description" : "기존 스케줄에서 Plan들을 복사하여 다른 스케줄에 복사합니다. json의 newScheduleId키에 특정 schedule Id를 포함하여 전송하면 있으면 기존에 존재하던 스케줄에 plan을 추가해주고 newScheduleId키에 값을 넣지 않으면 새로 스케줄을 생성한 뒤 그 스케줄에 Plan을 추가하여 줍니다",
        "operationId" : "Copy Plan to ",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/schedules-plans-copy386795774"
              },
              "examples" : {
                "Copy Plan to New Schedule" : {
                  "value" : "{\r\n  \"userId\" : \"park1\",\r\n  \"planIdList\" : [ 13, 60, 61 ],\r\n  \"scheduleId\" : 1\r\n}"
                },
                "Copy Plan to Old Schedule" : {
                  "value" : "{\r\n  \"userId\" : \"park1\",\r\n  \"planIdList\" : [ 13, 60, 61 ],\r\n  \"scheduleId\" : 1,\r\n  \"newScheduleId\" : 2\r\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/schedules-plans-copy-996633149"
                },
                "examples" : {
                  "Copy Plan to New Schedule" : {
                    "value" : "{\r\n  \"code\" : 2000,\r\n  \"message\" : \"성공\",\r\n  \"data\" : {\r\n    \"scheduleId\" : 2\r\n  }\r\n}"
                  },
                  "Copy Plan to Old Schedule" : {
                    "value" : "{\r\n  \"code\" : 2000,\r\n  \"message\" : \"성공\",\r\n  \"data\" : {\r\n    \"scheduleId\" : 2\r\n  }\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/users/travel-types" : {
      "post" : {
        "tags" : [ "users" ],
        "summary" : "여행 타입 부여",
        "description" : "사용자의 응답을 기반으로 여행 타입을 부여합니다.",
        "operationId" : "Grant TravelType",
        "requestBody" : {
          "content" : {
            "application/json;charset=UTF-8" : {
              "schema" : {
                "$ref" : "#/components/schemas/users-travel-types2059588752"
              },
              "examples" : {
                "Grant TravelType" : {
                  "value" : "{\r\n  \"id\" : \"park1\",\r\n  \"answer\" : [ \"b\", \"b\", \"b\", \"b\", \"b\", \"b\", \"b\", \"b\", \"b\", \"b\" ]\r\n}"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "200",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/users-travel-types-450931574"
                },
                "examples" : {
                  "Grant TravelType" : {
                    "value" : "{\r\n  \"code\" : 2000,\r\n  \"message\" : \"성공\",\r\n  \"data\" : {\r\n    \"typeName\" : \"양귀비<위로, 위안>\",\r\n    \"typeDesc\" : \"도비 이즈 프리!! 도비는 자유를 찾아 떠나요~ 양귀비의 꽃말 위로, 위안과 잘 어울리는 당신! 여행을 떠날때는 주로 위로와 위안을 찾아 떠납니다. 당신은 때로는 힘든 생기면 도피하고 싶은 경향이 있습니다. 힘든 일상으로부터 도망치듯이 여행을 떠나는 당신은 여행에서 휴식과 편안함을 가장 중요하게 여기네요. 당신에게 추천하는 여행은 주로 호캉스, 스파, 식물원 등이 있습니다~\",\r\n    \"typeImageUrl\" : \"https://petra-bucket.s3.ap-northeast-2.amazonaws.com/psychology-test/%EB%A9%A7%EB%B0%AD%EC%A5%90.jpg\"\r\n  }\r\n}"
                  }
                }
              }
            }
          }
        }
      }
    }
  },
  "components" : {
    "schemas" : {
      "schedules-plans-copy386795774" : {
        "type" : "object",
        "properties" : {
          "newScheduleId" : {
            "type" : "number",
            "description" : "plan들을 복사할 기존의 Schedule Id"
          },
          "planIdList" : {
            "type" : "array",
            "description" : "복사할 Plan의 Id 리스트",
            "items" : {
              "oneOf" : [ {
                "type" : "object"
              }, {
                "type" : "boolean"
              }, {
                "type" : "string"
              }, {
                "type" : "number"
              } ]
            }
          },
          "userId" : {
            "type" : "string",
            "description" : "사용자 ID"
          },
          "scheduleId" : {
            "type" : "number",
            "description" : "PlanId가 위치한 Schedule Id"
          }
        }
      },
      "users-travel-types2059588752" : {
        "type" : "object",
        "properties" : {
          "answer" : {
            "type" : "array",
            "description" : "밸런스 게임 응답",
            "items" : {
              "oneOf" : [ {
                "type" : "object"
              }, {
                "type" : "boolean"
              }, {
                "type" : "string"
              }, {
                "type" : "number"
              } ]
            }
          },
          "id" : {
            "type" : "string",
            "description" : "사용자 ID"
          }
        }
      },
      "users-travel-types-450931574" : {
        "type" : "object",
        "properties" : {
          "code" : {
            "type" : "number",
            "description" : "응답코드"
          },
          "data" : {
            "type" : "object",
            "properties" : {
              "typeDesc" : {
                "type" : "string",
                "description" : "여행 타입 설명"
              },
              "typeImageUrl" : {
                "type" : "string",
                "description" : "여행 타입 이미지"
              },
              "typeName" : {
                "type" : "string",
                "description" : "여행 타입 이름"
              }
            }
          },
          "message" : {
            "type" : "string",
            "description" : "응답메시지"
          }
        }
      },
      "schedule-scheduleId1469518433" : {
        "type" : "object",
        "properties" : {
          "code" : {
            "type" : "number",
            "description" : "응답코드"
          },
          "data" : {
            "type" : "object",
            "properties" : {
              "endDate" : {
                "type" : "string",
                "description" : "스케줄 종료일자"
              },
              "planList" : {
                "type" : "array",
                "items" : {
                  "type" : "object",
                  "properties" : {
                    "attraction" : {
                      "type" : "object",
                      "properties" : {
                        "address" : {
                          "type" : "string",
                          "description" : "관광지 주소"
                        },
                        "name" : {
                          "type" : "string",
                          "description" : "관광지 이름"
                        },
                        "id" : {
                          "type" : "number",
                          "description" : "관광지 idx"
                        },
                        "category" : {
                          "type" : "string",
                          "description" : "관광지 카테고리"
                        }
                      }
                    },
                    "endDate" : {
                      "type" : "string",
                      "description" : "계획 종료일자"
                    },
                    "memo" : {
                      "type" : "string",
                      "description" : "계획 메모"
                    },
                    "id" : {
                      "type" : "number",
                      "description" : "계획 idx"
                    },
                    "startDate" : {
                      "type" : "string",
                      "description" : "계획 시작일자"
                    },
                    "order" : {
                      "type" : "number",
                      "description" : "계획의 순서"
                    }
                  }
                }
              },
              "id" : {
                "type" : "number",
                "description" : "스케줄 idx"
              },
              "adult" : {
                "type" : "number",
                "description" : "어른 인원수"
              },
              "title" : {
                "type" : "string",
                "description" : "스케줄 제목"
              },
              "startDate" : {
                "type" : "string",
                "description" : "스케줄 시작일자"
              },
              "child" : {
                "type" : "number",
                "description" : "아이 인원수"
              }
            }
          },
          "message" : {
            "type" : "string",
            "description" : "응답메시지"
          }
        }
      },
      "mypage2104152416" : {
        "type" : "object",
        "properties" : {
          "code" : {
            "type" : "number",
            "description" : "응답코드"
          },
          "data" : {
            "type" : "object",
            "properties" : {
              "myReviewCount" : {
                "type" : "number",
                "description" : "내가 작성한 review 수"
              },
              "myScheduleCount" : {
                "type" : "number",
                "description" : "내가 작성한 schedule 수"
              },
              "userId" : {
                "type" : "string",
                "description" : "내 id"
              }
            }
          },
          "message" : {
            "type" : "string",
            "description" : "응답메시지"
          }
        }
      },
      "schedules-plans-copy-996633149" : {
        "type" : "object",
        "properties" : {
          "code" : {
            "type" : "number",
            "description" : "응답코드"
          },
          "data" : {
            "type" : "object",
            "properties" : {
              "scheduleId" : {
                "type" : "number",
                "description" : "기존의 Schedule Id"
              }
            }
          },
          "message" : {
            "type" : "string",
            "description" : "응답메시지"
          }
        }
      },
      "login-1664081379" : {
        "type" : "object",
        "properties" : {
          "code" : {
            "type" : "number",
            "description" : "응답코드"
          },
          "message" : {
            "type" : "string",
            "description" : "응답메시지"
          }
        }
      },
      "login486549215" : {
        "type" : "object"
      }
    }
  }
}